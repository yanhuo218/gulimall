package com.yanhuo.coupon.dao;

import com.yanhuo.coupon.entity.CouponHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 * 
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-22 09:00:56
 */
@Mapper
public interface CouponHistoryDao extends BaseMapper<CouponHistoryEntity> {
	
}
