package com.yanhuo.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mysql.cj.util.StringUtils;
import com.yanhuo.common.constant.ProductConstent;
import com.yanhuo.product.dao.AttrAttrgroupRelationDao;
import com.yanhuo.product.dao.AttrGroupDao;
import com.yanhuo.product.dao.CategoryDao;
import com.yanhuo.product.entity.AttrAttrgroupRelationEntity;
import com.yanhuo.product.entity.AttrGroupEntity;
import com.yanhuo.product.entity.CategoryEntity;
import com.yanhuo.product.service.CategoryService;
import com.yanhuo.product.vo.AttrRespVo;
import com.yanhuo.product.vo.AttrVo;
import com.yanhuo.product.vo.AttrgroupRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.common.utils.Query;

import com.yanhuo.product.dao.AttrDao;
import com.yanhuo.product.entity.AttrEntity;
import com.yanhuo.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;
    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId, String type) {
        String key = (String) params.get("key");
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_type", "base".equalsIgnoreCase(type) ? ProductConstent.AttrEnum.ATTR_TYPE_BASE.getCode() : ProductConstent.AttrEnum.ATTR_TYPE_SALE.getCode());
        if (catelogId != 0) {
            queryWrapper.eq("catelog_id", catelogId);
        }
        if (!StringUtils.isNullOrEmpty(key)) {
            queryWrapper.and((obj) -> {
                obj.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        List<AttrEntity> list = page.getRecords();
        List<AttrRespVo> collect = list.stream().map((item) -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(item, attrRespVo);
            QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id", item.getAttrId());
            if ("base".equalsIgnoreCase(type)) {
                AttrAttrgroupRelationEntity relation = relationDao.selectOne(wrapper);
                if (relation != null) {
                    AttrGroupEntity attrGroup = attrGroupDao.selectById(relation.getAttrGroupId());
                    attrRespVo.setGroupName(attrGroup.getAttrGroupName());
                }
            }


            CategoryEntity categoryEntity = categoryDao.selectById(item.getCatelogId());
            if (categoryEntity != null) {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(collect);
        return pageUtils;
    }

    @Override
    public PageUtils getNoRelation(Map<String, Object> params, String attrgroupId) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();

        List<Long> collect = relationDao.selectList(null).stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        if (collect.size() > 0) wrapper.notIn("attr_id", collect);
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper.eq("attr_type", ProductConstent.AttrEnum.ATTR_TYPE_BASE.getCode())
        );
        return new PageUtils(page);
    }


    @Override
    @Transactional
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.save(attrEntity);
        if (attr.getAttrType() == ProductConstent.AttrEnum.ATTR_TYPE_BASE.getCode() && attr.getAttrGroupId() != null) {
            AttrAttrgroupRelationEntity relation = new AttrAttrgroupRelationEntity();
            relation.setAttrId(attrEntity.getAttrId());
            relation.setAttrGroupId(attr.getAttrGroupId());
            relationDao.insert(relation);
        }
    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        AttrRespVo respVo = new AttrRespVo();
        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity, respVo);

        AttrAttrgroupRelationEntity relation = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
        if (relation != null) {
            respVo.setAttrGroupId(relation.getAttrGroupId());
            AttrGroupEntity attrGroup = attrGroupDao.selectById(relation.getAttrGroupId());
            if (attrGroup != null && attrGroup.getAttrGroupId() != null) {
                respVo.setGroupName(attrGroup.getAttrGroupName());
            }
        }
        Long[] path = categoryService.findPath(respVo.getCatelogId());
        respVo.setCatelogPath(path);
        CategoryEntity categoryEntity = categoryDao.selectById(respVo.getCatelogId());
        if (categoryEntity != null) {
            respVo.setCatelogName(categoryEntity.getName());
        }
        return respVo;
    }

    @Override
    @Transactional
    public void updateAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.updateById(attrEntity);
        if (attr.getAttrType() == ProductConstent.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity relation = new AttrAttrgroupRelationEntity();
            relation.setAttrGroupId(attr.getAttrGroupId());
            relation.setAttrId(attr.getAttrId());
            Long attrId = relationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            if (attrId > 0) {
                relationDao.update(relation, new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            } else {
                relationDao.insert(relation);
            }
        }
    }

    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        return relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId)).stream().map((attr) -> this.getById(attr.getAttrId())).collect(Collectors.toList());
    }

    @Override
    public void deleteRelation(List<AttrgroupRelationVo> attrgroupRelation) {
        List<AttrAttrgroupRelationEntity> collect = attrgroupRelation.stream().map((item) -> {
            AttrAttrgroupRelationEntity relation = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relation);
            return relation;
        }).collect(Collectors.toList());
        relationDao.deleteBatchRelation(collect);
    }

}