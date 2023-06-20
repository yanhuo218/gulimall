package com.yanhuo.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.yanhuo.common.validator.group.AddGroup;
import com.yanhuo.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 属性&属性分组关联
 * 
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
@Data
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@Null(message = "新增时不能指定id", groups = {AddGroup.class})
	@NotNull(message = "修改时id不能为空", groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 属性id
	 */
	@NotNull(message = "属性id不能为空", groups = {AddGroup.class})
	private Long attrId;
	/**
	 * 属性分组id
	 */
	@NotNull(message = "属性分组id不能为空", groups = {AddGroup.class})
	private Long attrGroupId;
	/**
	 * 属性组内排序
	 */
	private Integer attrSort;

}
