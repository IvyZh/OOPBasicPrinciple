package com.ivyzh.oopbasicprinciple.srp.imageloader3.cache;

import android.graphics.Bitmap;

/**
 * 图片缓存接口
 */

public interface IImageCache {

    Bitmap get(String url);

    void put(String url, Bitmap bmp);
}
