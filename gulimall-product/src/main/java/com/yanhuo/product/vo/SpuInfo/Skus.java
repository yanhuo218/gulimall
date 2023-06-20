package com.yanhuo.product.vo.SpuInfo;

import com.yanhuo.common.to.MemberPrice;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Skus {

    private List<Attr> attr;
    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubtitle;
    private List<Images> images;
    private List<String> descar;
    private Long fullCount;
    private BigDecimal discount;
    private Long countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Long priceStatus;
    private List<MemberPrice> memberPrice;

}