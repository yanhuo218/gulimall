package com.yanhuo.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.product.entity.SpuInfoDescEntity;
import com.yanhuo.product.entity.SpuInfoEntity;
import com.yanhuo.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-21 20:06:11
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo spuInfo);

    void saveBatchSpuInfo(SpuInfoEntity spuInfoEntity);

}

