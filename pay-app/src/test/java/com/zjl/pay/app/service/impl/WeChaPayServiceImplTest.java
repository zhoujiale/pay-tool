package com.zjl.pay.app.service.impl;

import com.zjl.pay.app.PayServiceApplication;
import com.zjl.pay.app.service.WeChatPayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PayServiceApplication.class)
class WeChaPayServiceImplTest {

    @Autowired
    private WeChatPayService weChatPayService;

    @Test
    void testAutoConfig() {
        weChatPayService.testAutoConfig();
    }
}