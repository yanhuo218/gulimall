package com.yanhuo.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.product.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

