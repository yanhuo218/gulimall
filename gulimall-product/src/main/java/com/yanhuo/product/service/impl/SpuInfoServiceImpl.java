package com.yanhuo.product.service.impl;

import com.yanhuo.common.to.SkuReductionTo;
import com.yanhuo.common.to.SpuBoundTo;
import com.yanhuo.product.entity.*;
import com.yanhuo.product.feign.CouponFeignService;
import com.yanhuo.product.service.*;
import com.yanhuo.product.vo.SpuInfo.*;
import com.yanhuo.product.vo.SpuSaveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.common.utils.Query;

import com.yanhuo.product.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {
    private final SpuInfoDescService spuInfoDescService;
    private final SpuImagesService spuImagesService;
    private final ProductAttrValueService productAttrValueService;
    private final SkuInfoService skuInfoService;
    private final SkuImagesService skuImagesService;
    private final SkuSaleAttrValueService saleAttrValueService;
    private final CouponFeignService couponFeignService;


    @Autowired
    public SpuInfoServiceImpl(SpuInfoDescService spuInfoDescService, SpuImagesService spuImagesService, ProductAttrValueService productAttrValueService,
                              SkuInfoService skuInfoService, SkuImagesService skuImagesService, SkuSaleAttrValueService saleAttrValueService,
                              CouponFeignService couponFeignService) {
        this.spuInfoDescService = spuInfoDescService;
        this.spuImagesService = spuImagesService;
        this.productAttrValueService = productAttrValueService;
        this.skuInfoService = skuInfoService;
        this.skuImagesService = skuImagesService;
        this.saleAttrValueService = saleAttrValueService;
        this.couponFeignService = couponFeignService;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveSpuInfo(SpuSaveVo spuInfo) {
        //------------------1、保存spu基本信息 pms_spu_info------------------
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuInfo, spuInfoEntity);
        this.save(spuInfoEntity);
        //------------------2、保存spu的描述图片 pms_spu_info_desc------------------
        List<String> decript = spuInfo.getDecript();
        if (decript.size() > 0) {
            SpuInfoDescEntity desc = new SpuInfoDescEntity();
            desc.setSpuId(spuInfoEntity.getId());
            desc.setDecript(String.join(",", decript));
            spuInfoDescService.saveSpuInfoDesc(desc);
        }
        //------------------3、保存spu的图片集 pms_spu_images------------------
        spuImagesService.saveSpuImages(spuInfoEntity.getId(), spuInfo.getImages());
        //------------------4、保存spu的规格参数 pms_product_attr_value------------------
        productAttrValueService.saveProductAttrValue(spuInfoEntity.getId(), spuInfo.getBaseAttrs());
        //------------------5、保存spu的积分信息 gulimall-coupon ==> sms_spu_bounds------------------
        Bounds bounds = spuInfo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds, spuBoundTo);
        spuBoundTo.setSpuId(spuInfoEntity.getId());
        couponFeignService.saveSkuBounds(spuBoundTo);
        //------------------6、保存当前spu对应的所有sku信息----------------------
        List<Skus> skus = spuInfo.getSkus();
        if (skus != null && skus.size() > 0) {
            //------------------6.1、保存sku的基本信息 pms_sku_info------------------
            skus.forEach(item -> {
                String DefaultImg = "";
                for (Images image : item.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        DefaultImg = image.getImgUrl();
                    }
                }
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setSpuId(spuInfoEntity.getId());
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSkuDefaultImg(DefaultImg);
                skuInfoService.saveSkuInfo(skuInfoEntity);
                //------------------6.2、保存sku的图片信息 pms_sku_images------------------
                Long skuId = skuInfoEntity.getSkuId();
                List<SkuImagesEntity> imagesEntities = item.getImages().stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    BeanUtils.copyProperties(img, skuImagesEntity);
                    skuImagesEntity.setSkuId(skuId);
                    return skuImagesEntity;
                }).collect(Collectors.toList());
                skuImagesService.saveBatch(imagesEntities);
                //------------------6.3、保存sku的销售属性信息 pms_sku_sale_attr_value------------------
                List<Attr> attrs = item.getAttr();
                List<SkuSaleAttrValueEntity> valueEntityList = attrs.stream().map(attr -> {
                    SkuSaleAttrValueEntity saleAttrValue = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(attr, saleAttrValue);
                    saleAttrValue.setSkuId(skuId);
                    saleAttrValue.setAttrSort(0);
                    return saleAttrValue;
                }).collect(Collectors.toList());
                saleAttrValueService.saveBatch(valueEntityList);
                //------------------6.4、保存sku的优惠、满减等信息 gulimall-coupon ------------------
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(item, skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                if (skuReductionTo.getFullCount() <= 0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) > 0) {
                    couponFeignService.saveSkuReduction(skuReductionTo);
                }
            });
        }

    }

    @Override
    public void saveBatchSpuInfo(SpuInfoEntity spuInfoEntity) {
        baseMapper.insert(spuInfoEntity);
    }


}