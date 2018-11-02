package com.ivyzh.oopbasicprinciple.srp.simple5;

/**
 * Created by Ivy on 2018/11/1.
 */

public abstract class MyCallBack<T> {// 注意：这里第一次写的时候，是把MyCallBack写成了接口。发现不对。在调用Utils.analysisClazzInfo方法是报错。

    public abstract void onSuccess(T t);//正确返回数据code=200

    public abstract void onFailure(Exception e);//url访问失败

    public abstract void onError(int errorCode);//code!=200
}

//public interface MyCallBack<T> {// 报错版本
//
//    void onSuccess(T t);//正确返回数据code=200
//
//    void onFailure(Exception e);//url访问失败
//
//    void onError(int errorCode);//code!=200
//}
// 报错信息：
//    Caused by: java.lang.ClassCastException: java.lang.Class cannot be cast to java.lang.reflect.ParameterizedType
//        at com.ivyzh.oopbasicprinciple.srp.simple5.Utils.analysisClazzInfo(Utils.java:20)