package com.yanhuo.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.product.entity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

