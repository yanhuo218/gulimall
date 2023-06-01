package com.yanhuo.thirdparty.entity;

import lombok.Data;

@Data
public class TemporaryKey {
    private String tmpSecretId;
    private String tmpSecretKey;
    private String sessionToken;
}
