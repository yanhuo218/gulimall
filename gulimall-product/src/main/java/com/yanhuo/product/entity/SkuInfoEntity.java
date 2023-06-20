package com.yanhuo.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sku信息
 * 
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
@Data
@TableName("pms_sku_info")
public class SkuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * skuId  0
	 */
	@TableId
	private Long skuId;
	/**
	 * spuId   1
	 */
	private Long spuId;
	/**
	 * sku名称  0
	 */
	private String skuName;
	/**
	 * sku介绍描述  0
	 */
	private String skuDesc;
	/**
	 * 所属分类id  1
	 */
	private Long catalogId;
	/**
	 * 品牌id  1
	 */
	private Long brandId;
	/**
	 * 默认图片  1
	 */
	private String skuDefaultImg;
	/**
	 * 标题 0
	 */
	private String skuTitle;
	/**
	 * 副标题 0
	 */
	private String skuSubtitle;
	/**
	 * 价格 0
	 */
	private BigDecimal price;
	/**
	 * 销量 1
	 */
	private Long saleCount;

}
