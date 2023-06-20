package com.yanhuo.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuReductionTo {
    private Long skuId;
    private Long fullCount;
    private BigDecimal discount;
    private Integer countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Long priceStatus;
    private List<MemberPrice> memberPrice;
}
