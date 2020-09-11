package com.lqw.redisui.utils;

public class StringUtils {

    public static boolean isBlank(String str){
        return (str == null || "".equals(str) || str.replaceAll(" ","").trim().length() == 0);
    }
}
