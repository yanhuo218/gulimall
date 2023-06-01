package com.yanhuo.thirdparty;


import java.util.TreeMap;

import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.yanhuo.thirdparty.utils.TenXunCosUtil.*;

@SpringBootTest
public class GulimallThirdPartyApplicationTests {

    /**
     * 基本的临时密钥申请示例，适合对一个桶内的一批对象路径，统一授予一批操作权限
     */
    @Test
    public void testGetCredential() {
        TreeMap<String, Object> config = new TreeMap<>();
        try {
            config.put("secretId", "AKID0xkX8UedOvNc6QZXVGqFQN4YeZIyS7Yk");
            config.put("secretKey", SECRET_KEY);
            config.put("durationSeconds", 1800);
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
            Response response = CosStsClient.getCredential(config);
            System.out.println(response.credentials.tmpSecretId);
            System.out.println(response.credentials.tmpSecretKey);
            System.out.println(response.credentials.sessionToken);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("no valid secret !");
        }
    }

}