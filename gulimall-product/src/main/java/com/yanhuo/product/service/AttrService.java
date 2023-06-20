package com.yanhuo.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.product.entity.AttrEntity;
import com.yanhuo.product.vo.AttrRespVo;
import com.yanhuo.product.vo.AttrVo;
import com.yanhuo.product.vo.AttrgroupRelationVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params, Long catelogId, String attrtype);

    PageUtils getNoRelation(Map<String, Object> params, String attrgroupId);


    void saveAttr(AttrVo attr);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(List<AttrgroupRelationVo> attrgroupRelation);

}

