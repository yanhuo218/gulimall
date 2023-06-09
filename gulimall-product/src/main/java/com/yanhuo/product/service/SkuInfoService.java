package com.yanhuo.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.product.entity.SkuInfoEntity;
import com.yanhuo.product.entity.SpuInfoEntity;
import com.yanhuo.product.vo.SpuSaveVo;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);



    void saveSkuInfo(SkuInfoEntity skuInfoEntity);
}

