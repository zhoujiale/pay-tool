package com.zjl.pay.app.service.impl;

import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.zjl.pay.api.config.WeChatPayConfiguration;
import com.zjl.pay.app.service.WeChatPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @classname: WeChaPayServiceImpl
 * @author: zhou
 * @description:
 * @date: 2023/6/20 13:52
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WeChaPayServiceImpl implements WeChatPayService {


    private final WeChatPayConfiguration weChatPayConfiguration;

    private final JsapiServiceExtension jsapiServiceExtension;

    @Override
    public void testAutoConfig() {
        System.out.println(weChatPayConfiguration.getAppid());
    }

    @Override
    public void weChatJSPrePay() {
        jsapiServiceExtension.prepayWithRequestPayment(new PrepayRequest());
    }
}
