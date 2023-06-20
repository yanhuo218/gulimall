package com.yanhuo.product.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.yanhuo.common.validator.group.AddGroup;
import com.yanhuo.common.validator.group.UpdateGroup;
import com.yanhuo.product.entity.AttrAttrgroupRelationEntity;
import com.yanhuo.product.entity.AttrEntity;
import com.yanhuo.product.service.AttrAttrgroupRelationService;
import com.yanhuo.product.service.AttrService;
import com.yanhuo.product.service.CategoryService;
import com.yanhuo.product.vo.AttrGroupWithAttrsVo;
import com.yanhuo.product.vo.AttrgroupRelationVo;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.yanhuo.product.entity.AttrGroupEntity;
import com.yanhuo.product.service.AttrGroupService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.common.utils.R;


/**
 * 属性分组
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
@Slf4j
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService relationService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Long catelogId) {
        PageUtils page = attrGroupService.getDateList(params, catelogId);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long[] path = categoryService.findPath(attrGroup.getCatelogId());
        attrGroup.setCatelogPath(path);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@Validated({AddGroup.class}) @RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable Long catelogId){
        List<AttrGroupWithAttrsVo> data = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data",data);
    }


    @RequestMapping("/attr/relation")
    public R attrAddRelation(@Validated({AddGroup.class}) @RequestBody List<AttrgroupRelationVo> AttrgroupRelation) {
        relationService.saveBatchRelation(AttrgroupRelation);
        return R.ok();
    }

    @RequestMapping("/attr/relation/delete")
    public R attrDeleteRelation(@Validated({UpdateGroup.class}) @RequestBody List<AttrgroupRelationVo> AttrgroupRelation) {
        attrService.deleteRelation(AttrgroupRelation);
        return R.ok();
    }
    @RequestMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable Long attrgroupId) {
        List<AttrEntity> attrs = attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data",attrs);
    }

    @RequestMapping("/{attrgroupId}/noattr/relation")
    public R noattrRelation(@RequestParam Map<String, Object> params, @PathVariable String attrgroupId) {
        PageUtils page = attrService.getNoRelation(params, attrgroupId);
        return R.ok().put("page", page);
    }
}
