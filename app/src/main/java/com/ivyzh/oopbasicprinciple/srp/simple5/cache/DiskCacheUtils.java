package com.ivyzh.oopbasicprinciple.srp.simple5.cache;

/**
 * Created by Ivy on 2018/11/1.
 */

public class DiskCacheUtils implements ICacheUtils {

    @Override
    public String get(String url) {
        String result = "{\n" +
                "\t\"name\": \"DiskCache\",\n" +
                "\t\"age\": 22\n" +
                "}";
        return result;
    }

    @Override
    public void set(String url, String value) {

    }

    @Override
    public String toString() {
        return "Disk Cache";
    }
}
