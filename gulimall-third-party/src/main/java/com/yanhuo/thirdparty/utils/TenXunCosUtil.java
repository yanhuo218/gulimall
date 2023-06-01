package com.yanhuo.thirdparty.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class TenXunCosUtil implements InitializingBean {

    @Value("${spring.cos.appid}")
    private String appid;
    @Value("${spring.cos.secretId}")
    private String secretId;
    @Value("${spring.cos.secretKey}")
    private String SecretKey;
    @Value("${spring.cos.regionName}")
    private String regionName;
    @Value("${spring.cos.bucketName}")
    private String bucketName;
    @Value("${spring.cos.path}")
    private String path;

    public static String APPID;
    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String REGION_NAME;
    public static String BUCKET_NAME;
    public static String PATH;

    @Override
    public void afterPropertiesSet() {
        APPID = appid;
        SECRET_ID = secretId;
        SECRET_KEY = SecretKey;
        REGION_NAME = regionName;
        BUCKET_NAME = bucketName;
        PATH = path;
    }
}
