package com.yanhuo.product.vo;

import com.yanhuo.product.vo.SpuInfo.BaseAttrs;
import com.yanhuo.product.vo.SpuInfo.Bounds;
import com.yanhuo.product.vo.SpuInfo.Skus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SpuSaveVo {

    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private Long publishStatus;
    private List<String> decript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;
}