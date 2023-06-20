package com.yanhuo.product.vo;

import lombok.Data;

@Data
public class AttrVo {
    private Long attrId;
    private Long attrGroupId;
    private String attrName;
    private Integer searchType;
    private Integer valueType;
    private String icon;
    private String valueSelect;
    private Integer attrType;
    private Long enable;
    private Long catelogId;
    private Integer showDesc;
}
