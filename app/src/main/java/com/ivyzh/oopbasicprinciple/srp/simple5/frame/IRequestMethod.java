package com.ivyzh.oopbasicprinciple.srp.simple5.frame;

import java.io.File;
import java.util.Map;

import com.ivyzh.oopbasicprinciple.srp.simple5.MyCallBack;


/**
 * 封装了基本的请求方式
 */

public interface IRequestMethod {

    <T> void get(String url, boolean isCache, MyCallBack<T> callBack);

    <T> void post(String url, boolean isCache, MyCallBack<T> callBack, Map<String, String> params);

    void upload(String url, File f, Map<String, String> params);

    void download(String url, String path);

    //...
}
