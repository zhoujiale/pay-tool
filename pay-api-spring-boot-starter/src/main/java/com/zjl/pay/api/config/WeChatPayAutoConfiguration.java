package com.zjl.pay.api.config;

import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wechat.pay.java.core.RSAConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

/**
 * @classname: WeChatPayAutoConfiguration
 * @author: zhou
 * @description:
 * @date: 2023/6/20 13:43
 */
@Slf4j
@ConditionalOnClass(WeChatPayConfiguration.class)
@EnableConfigurationProperties(WeChatPayProperties.class)
@Configuration
public class WeChatPayAutoConfiguration {

    private CloseableHttpClient httpClient;

    @Bean
    public WeChatPayConfiguration weChatPayConfiguration(WeChatPayProperties weChatPayProperties) throws GeneralSecurityException, IOException, HttpCodeException {
        WeChatPayConfiguration weChatPayConfiguration = new WeChatPayConfiguration();
        weChatPayConfiguration.setAppid(weChatPayProperties.getAppid());
        weChatPayConfiguration.setMchId(weChatPayProperties.getMchId());
        weChatPayConfiguration.setApiKey(weChatPayProperties.getApiKey());
        weChatPayConfiguration.setMchSerialNo(weChatPayProperties.getMchSerialNo());
        weChatPayConfiguration.setMerchantPrivateKey(weChatPayProperties.getMerchantPrivateKey());
        weChatPayConfiguration.setNotifyUrl(weChatPayProperties.getNotifyUrl());
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
        PrivateKey privateKey = PemUtil.loadPrivateKey(new ByteArrayInputStream(
                weChatPayConfiguration.getMerchantPrivateKey().getBytes(StandardCharsets.UTF_8)));
        certificatesManager.putMerchant(weChatPayConfiguration.getMchId(),new WechatPay2Credentials(weChatPayConfiguration.getMchId(),
                new PrivateKeySigner(weChatPayConfiguration.getMchSerialNo(), privateKey)),weChatPayConfiguration.getApiKey().getBytes(StandardCharsets.UTF_8));
        return weChatPayConfiguration;
    }

    @Bean
    public JsapiServiceExtension jsapiServiceExtension(WeChatPayConfiguration weChatPayConfiguration){
        RSAConfig config = new RSAConfig.Builder()
                .merchantId(weChatPayConfiguration.getMchId())
                .privateKeyFromPath(weChatPayConfiguration.getMerchantPrivateKey())
                .merchantSerialNumber(weChatPayConfiguration.getMchSerialNo())
                .wechatPayCertificatesFromPath(weChatPayConfiguration.getPayCertificate())
                .build();
        return new JsapiServiceExtension.Builder().config(config).build();
    }

//    public void close() throws IOException {
//
//        httpClient.close();
//        log.info("httpClient close success");
//    }
}
