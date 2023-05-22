package com.yanhuo.ware.dao;

import com.yanhuo.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-22 09:12:22
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
