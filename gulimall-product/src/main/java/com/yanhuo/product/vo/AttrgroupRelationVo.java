package com.yanhuo.product.vo;

import com.yanhuo.common.validator.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AttrgroupRelationVo {
    @NotNull(message = "属性id不能为空", groups = {AddGroup.class})
    private Long attrId;
    /**
     * 属性分组id
     */
    @NotNull(message = "属性分组id不能为空", groups = {AddGroup.class})
    private Long attrGroupId;
}
