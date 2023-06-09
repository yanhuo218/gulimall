package com.yanhuo.product.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yanhuo.product.entity.CategoryEntity;
import com.yanhuo.product.service.CategoryService;
import com.yanhuo.common.utils.R;


/**
 * 商品三级分类
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list/tree")
    public R list() {
        List<CategoryEntity> list = categoryService.listWithTree();
        return R.ok().put("data", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    //@RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);
        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category) {
        category.setProductCount(0);
        if (category.getShowStatus() == null) {
            category.setShowStatus(1);
        }
        if (category.getSort() == null) {
            category.setSort(0);
        }
        categoryService.save(category);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category) {
        categoryService.updateDetail(category);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds) {
        categoryService.removeMenuByIds(Arrays.asList(catIds));
        return R.ok();
    }

    @RequestMapping("/undisable")
    public R undisable(@RequestBody Long[] catIds) {
        categoryService.undisable(Arrays.asList(catIds));
        return R.ok();
    }

    //批量修改分类
    @RequestMapping("/update/sort")
    public R updateSort(@RequestBody CategoryEntity[] categoryEntities) {
        categoryService.updateBatchById(Arrays.asList(categoryEntities));
        return R.ok();
    }
}
