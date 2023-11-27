package com.zjl.pay.api.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @classname: DateUtil
 * @author: zhou
 * @description:
 * @date: 2023/6/20 16:33
 */
@Slf4j
public class DateUtils {

    public static final String DATE_TIME_MILL = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String localDateToStr(LocalDateTime localDateTime){
        return String.valueOf(localDateTime.getYear()) + (localDateTime.getMonthValue() < 10 ? "0" + localDateTime.getMonthValue():localDateTime.getMonthValue())
                + localDateTime.getDayOfMonth() +
                localDateTime.getHour() + localDateTime.getMinute() + localDateTime.getSecond();
    }

}
