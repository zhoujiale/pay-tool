package com.zjl.pay.api.config;

import lombok.Data;

/**
 * @classname: WeChatPayConfiguration
 * @author: zhou
 * @description:
 * @date: 2023/6/20 13:58
 */
@Data
public class WeChatPayConfiguration {

    private String appid;

    private String mchId;

    private String apiKey;

    private String merchantPrivateKey;

    private String payCertificate;

    private String mchSerialNo;

    private String notifyUrl;
}
