package com.zjl.pay.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @classname: WeChatPayAutoConfiguration
 * @author: zhou
 * @description: weChat pay
 * @date: 2023/6/20 12:26
 */
@Data
@ConfigurationProperties(prefix = "wechat.pay")
public class WeChatPayProperties {

    private String appid;

    private String mchId;

    private String apiKey;

    private String merchantPrivateKey;

    private String payCertificate;

    private String mchSerialNo;

    private String notifyUrl;


}
