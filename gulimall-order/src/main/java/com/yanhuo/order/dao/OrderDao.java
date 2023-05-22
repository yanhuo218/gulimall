package com.yanhuo.order.dao;

import com.yanhuo.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-22 08:54:54
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
