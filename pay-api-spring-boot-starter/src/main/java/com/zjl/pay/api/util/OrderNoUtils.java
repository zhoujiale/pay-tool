package com.zjl.pay.api.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @classname: OrderNoUtil
 * @author: zhou
 * @description: 订单号生成工具
 * @date: 2023/6/20 15:55
 */
@Slf4j
public class OrderNoUtils {

    private static final String DEFAULT_ORDER_PREFIX = "OD";

    private static final String DEFAULT_REFUND_PREFIX = "RD";

    private static final int DEFAULT_RAND_LENGTH = 8;

    private static final String[] DEFAULT_CODE = {"1","2","3","4","5","6","7","8","9","0","a","b","c","d","e","f","g","h","i","j","k",
    "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R",
    "S","T","U","V","W","X","Y","Z"};

    public static String createOrderNo(Long userId,String orderPrefix){
        orderPrefix = null == orderPrefix || orderPrefix.length() != 2?DEFAULT_ORDER_PREFIX:orderPrefix;
        return orderPrefix + DateUtils.localDateToStr(LocalDateTime.now()) + getUserCode(userId,DEFAULT_RAND_LENGTH);
    }

    private static String getUserCode(Long userId,int limit){
        String id = userId.toString();
        return id + randomCode(limit - id.length());
    }

    private static String randomCode(int count){
        if (0 == count){
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0;i < count;i++) {
            stringBuffer.append(DEFAULT_CODE[ThreadLocalRandom.current().nextInt(DEFAULT_CODE.length)].toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(createOrderNo(1001L,null));
    }
}
