package com.yanhuo.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-22 08:54:54
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

