package com.yanhuo.member.dao;

import com.yanhuo.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-22 09:06:51
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
