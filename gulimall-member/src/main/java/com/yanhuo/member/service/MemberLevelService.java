package com.yanhuo.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-22 09:06:51
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

