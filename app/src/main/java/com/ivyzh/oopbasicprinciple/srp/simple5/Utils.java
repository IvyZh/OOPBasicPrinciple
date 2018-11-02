package com.ivyzh.oopbasicprinciple.srp.simple5;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Ivy on 2018/11/1.
 */

public class Utils {

    /**
     * 解析一个类上面的class信息
     */
    public static Class<?> analysisClazzInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }
}
