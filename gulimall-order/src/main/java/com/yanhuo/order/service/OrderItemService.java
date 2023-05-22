package com.yanhuo.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-22 08:54:54
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

