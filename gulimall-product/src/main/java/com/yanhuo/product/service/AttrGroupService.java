package com.yanhuo.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

