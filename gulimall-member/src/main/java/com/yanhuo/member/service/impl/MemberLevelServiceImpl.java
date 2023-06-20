package com.yanhuo.member.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.common.utils.Query;

import com.yanhuo.member.dao.MemberLevelDao;
import com.yanhuo.member.entity.MemberLevelEntity;
import com.yanhuo.member.service.MemberLevelService;


@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelDao, MemberLevelEntity> implements MemberLevelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<MemberLevelEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.like("name", key).or().eq("id", key).or().like("note", key);
        }
        IPage<MemberLevelEntity> page = this.page(
                new Query<MemberLevelEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}