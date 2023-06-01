package com.yanhuo.thirdparty.controller;

import com.tencent.cloud.Response;
import com.yanhuo.common.utils.R;
import com.yanhuo.thirdparty.service.CosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cos")
public class CosController {

    @Autowired
    private CosService cosService;

    @RequestMapping("/policy")
    public R getTempKey() {
        Response token = cosService.getToken(1800);
        return R.ok().put("key", token);
    }
}
