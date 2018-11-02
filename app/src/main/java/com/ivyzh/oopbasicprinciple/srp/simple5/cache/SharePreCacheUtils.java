package com.ivyzh.oopbasicprinciple.srp.simple5.cache;

/**
 * Created by Ivy on 2018/11/1.
 */

public class SharePreCacheUtils implements ICacheUtils {

    @Override
    public String get(String url) {
        String result = "{\n" +
                "\t\"name\": \"SPCache\",\n" +
                "\t\"age\": 18\n" +
                "}";
        return result;
    }

    @Override
    public void set(String url, String value) {

    }

    @Override
    public String toString() {
        return "Sp Cache";
    }
}
