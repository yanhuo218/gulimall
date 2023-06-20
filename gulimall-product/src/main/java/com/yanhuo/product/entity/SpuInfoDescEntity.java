package com.yanhuo.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * spu信息介绍
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
@Data
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {

    /**
     * 商品id
     */
    @TableId(value = "spu_id", type = IdType.INPUT)
    private Long spuId;
    /**
     * 商品介绍
     */
    private String decript;

}
