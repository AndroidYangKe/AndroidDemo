package com.android.yangke.java.m.utils;

import java.math.BigDecimal;

/**
 * author : yangke on 2021/3/22
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 数学相关的辅助类
 */
public class MathUtil {
    /**
     * 匹配是否为数字
     *
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @return 是否是数字
     */
    public static boolean isNumeric(String str) {
        try {
            String bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        return true;
    }
}
