package com.ivyzh.oopbasicprinciple.srp.simple2;

/**
 * Created by Ivy on 2018/11/1.
 */

public interface MyCallBack<T> {
    void onSuccess(T t);//正确返回数据code=200

    void onFailure(Exception e);//url访问失败

    void onError(int errorCode);//code!=200
}
