package com.yanhuo.product.service.impl;

import com.yanhuo.product.dao.AttrDao;
import com.yanhuo.product.vo.SpuInfo.BaseAttrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.common.utils.Query;

import com.yanhuo.product.dao.ProductAttrValueDao;
import com.yanhuo.product.entity.ProductAttrValueEntity;
import com.yanhuo.product.service.ProductAttrValueService;


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Autowired
    private AttrDao attrDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveProductAttrValue(Long id, List<BaseAttrs> baseAttrs) {
        if (baseAttrs != null && baseAttrs.size() > 0) {
            List<ProductAttrValueEntity> collect = baseAttrs.stream().map(item -> {
                ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
                productAttrValueEntity.setAttrId(item.getAttrId());
                productAttrValueEntity.setSpuId(id);
                productAttrValueEntity.setAttrValue(item.getAttrValues());
                productAttrValueEntity.setAttrName(attrDao.selectById(item.getAttrId()).getAttrName());
                productAttrValueEntity.setQuickShow(item.getShowDesc());
                return productAttrValueEntity;
            }).collect(Collectors.toList());
            this.saveBatch(collect);
        }
    }

}