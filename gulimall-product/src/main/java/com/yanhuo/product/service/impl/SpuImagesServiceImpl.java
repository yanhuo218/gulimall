package com.yanhuo.product.service.impl;

import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.common.utils.Query;

import com.yanhuo.product.dao.SpuImagesDao;
import com.yanhuo.product.entity.SpuImagesEntity;
import com.yanhuo.product.service.SpuImagesService;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(params),
                new QueryWrapper<SpuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuImages(Long id, List<String> images) {
        if (images != null && images.size() > 0) {
            List<SpuImagesEntity> collect = images.stream().map(item -> {
                SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
                spuImagesEntity.setSpuId(id);
                spuImagesEntity.setImgUrl(item);
                return spuImagesEntity;
            }).filter(item -> !StringUtils.isNullOrEmpty(item.getImgUrl())).collect(Collectors.toList());
            this.saveBatch(collect);
        }
    }
}