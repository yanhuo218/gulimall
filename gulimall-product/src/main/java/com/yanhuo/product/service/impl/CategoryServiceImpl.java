package com.yanhuo.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.common.utils.Query;

import com.yanhuo.product.dao.CategoryDao;
import com.yanhuo.product.entity.CategoryEntity;
import com.yanhuo.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(new Query<CategoryEntity>().getPage(params), new QueryWrapper<CategoryEntity>());

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {

        List<CategoryEntity> entities = baseMapper.selectListAll();
        return entities.stream().filter(categoryEntity -> {
            if (categoryEntity.getParentCid() != null) {
                return categoryEntity.getParentCid() == 0;
            }
            return false;
        }).peek(menu -> menu.setChildren(getCildrens(menu, entities))).sorted(Comparator.comparingInt(CategoryEntity::getSort)).collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> list) {
        //TODO
        baseMapper.deleteBatchIds(list);
    }

    @Override
    public void undisable(List<Long> list) {
        baseMapper.undisable(list);
    }

    @Override
    public Long[] findPath(Long catelogId) {
        List<Long> parentPath = findParentPath(catelogId, new ArrayList<>());
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[0]);
    }

    public List<Long> findParentPath(Long catelogId, List<Long> path) {
        path.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            path = findParentPath(byId.getParentCid(), path);
        }
        return path;
    }

    private List<CategoryEntity> getCildrens(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream().filter(categoryEntity ->
                Objects.equals(categoryEntity.getParentCid(), root.getCatId())
        ).peek(categoryEntity ->
                categoryEntity.setChildren(getCildrens(categoryEntity, all))
        ).sorted(Comparator.comparingInt(CategoryEntity::getSort)).collect(Collectors.toList());
    }

}