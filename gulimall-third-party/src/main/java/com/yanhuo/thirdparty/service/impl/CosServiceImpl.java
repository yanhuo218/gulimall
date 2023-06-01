package com.yanhuo.thirdparty.service.impl;

import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import com.yanhuo.thirdparty.service.CosService;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

import static com.yanhuo.thirdparty.utils.TenXunCosUtil.*;

@Service
public class CosServiceImpl implements CosService {
    @Override
    public Response getToken(Integer time) {
        TreeMap<String, Object> config = new TreeMap<>();
        try {
            config.put("secretId", SECRET_ID);
            config.put("secretKey", SECRET_KEY);
            config.put("durationSeconds", time);
            config.put("bucket", BUCKET_NAME);
            config.put("region", REGION_NAME);
            config.put("allowPrefixes", new String[]{"*"});
            String[] allowActions = new String[]{
                    // 简单上传
                    "name/cos:PutObject",
                    "name/cos:PostObject",
                    // 分片上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);
            return CosStsClient.getCredential(config);

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("no valid secret !");
        }
    }
}
