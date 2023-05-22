package com.yanhuo.product.dao;

import com.yanhuo.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
