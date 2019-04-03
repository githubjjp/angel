package com.pingan.stream.utils;

/**
 * 字符串工具类
 * @author chenhao
 * @date 2019.03.25
 */
public class StringUtils {

    /**
     * 判断字符串为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if(obj==null){
            return true;
        }else if(obj instanceof  String){
            if(obj.equals("")){
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串非空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }
}
