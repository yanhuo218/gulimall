package com.yanhuo.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.yanhuo.common.validator.group.AddGroup;
import com.yanhuo.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 属性分组
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    @TableId
    @Null(message = "新增时不能指定id", groups = {AddGroup.class})
    @NotNull(message = "修改时id不能为空", groups = {UpdateGroup.class})
    private Long attrGroupId;
    /**
     * 组名
     */
    @NotNull(message = "属性分组名不能为空", groups = {AddGroup.class})
    private String attrGroupName;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空", groups = {AddGroup.class})
    @Min(value = 0, message = "排序必须大于等于0", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;
    /**
     * 描述
     */
    @NotNull(message = "描述不能为空", groups = {AddGroup.class})
    private String descript;
    /**
     * 组图标
     */
    @NotNull(message = "组图标不能为空", groups = {AddGroup.class})
    private String icon;
    /**
     * 所属分类id
     */
    @NotNull(message = "所属分类id不能为空", groups = {AddGroup.class})
    private Long catelogId;

    /**
     * 分组路径
     */
    @TableField(exist = false)
    private Long[] catelogPath;

}
