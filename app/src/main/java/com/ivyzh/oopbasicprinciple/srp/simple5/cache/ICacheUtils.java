package com.ivyzh.oopbasicprinciple.srp.simple5.cache;

/**
 * Created by Ivy on 2018/11/1.
 */

public interface ICacheUtils {
    String get(String url);

    void set(String url, String value);
}
