package com.yanhuo.product.service.impl;

import com.yanhuo.product.controller.CategoryController;
import com.yanhuo.product.dao.BrandDao;
import com.yanhuo.product.dao.CategoryDao;
import com.yanhuo.product.entity.BrandEntity;
import com.yanhuo.product.service.BrandService;
import com.yanhuo.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.common.utils.Query;

import com.yanhuo.product.dao.CategoryBrandRelationDao;
import com.yanhuo.product.entity.CategoryBrandRelationEntity;
import com.yanhuo.product.service.CategoryBrandRelationService;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private BrandDao brandService;
    @Autowired
    private CategoryDao categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryBrandRelationEntity> getcatelogList(Long brandId) {
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId);
        return this.list(wrapper);
    }

    @Override
    public void saveCatelog(CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelation.setBrandName(brandService.selectById(categoryBrandRelation.getBrandId()).getName());
        categoryBrandRelation.setCatelogName(categoryService.selectById(categoryBrandRelation.getCatelogId()).getName());
        this.save(categoryBrandRelation);
    }

    @Override
    public void updateBrand(Long brandId, String name) {
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("brand_id",brandId);
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandId(brandId);
        categoryBrandRelationEntity.setBrandName(name);
        baseMapper.update(categoryBrandRelationEntity,wrapper);
    }

    @Override
    public void updateCategory(Long catId, String name) {
        baseMapper.updateCategory(catId,name);
    }

    @Override
    public List<BrandEntity> getBrandsByCatId(Long catId) {
        List<CategoryBrandRelationEntity> selectList = this.baseMapper.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
        return selectList.stream().filter(Objects::nonNull).map(item-> brandService.selectById(item.getBrandId())).collect(Collectors.toList());
    }

}