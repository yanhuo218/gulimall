package com.yanhuo.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-22 09:12:22
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

