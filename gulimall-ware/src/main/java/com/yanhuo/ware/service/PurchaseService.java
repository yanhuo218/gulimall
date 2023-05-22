package com.yanhuo.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.common.utils.PageUtils;
import com.yanhuo.ware.entity.PurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author yanhuo
 * @email yanhuo@qq.com
 * @date 2023-05-22 09:12:22
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

