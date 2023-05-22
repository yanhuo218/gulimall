package com.yanhuo.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.coupon.entity.SkuLadderEntity;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-22 09:00:56
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

