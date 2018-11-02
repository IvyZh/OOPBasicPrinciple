package com.ivyzh.oopbasicprinciple.srp.imageloader3.cache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.ivyzh.oopbasicprinciple.srp.imageloader3.cache.IImageCache;

/**
 * 内存缓存
 */

public class MemoryImageCache implements IImageCache {
    private String TAG = "MemoryImageCache";
    private LruCache<String, Bitmap> mImageCache;

    public MemoryImageCache() {
        int processors = Runtime.getRuntime().availableProcessors();
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//可使用最大内存
        int cacheSize = maxMemory / 4;// 去四分之一作为缓存
        Log.e(TAG, "init=" + processors + "," + maxMemory + "," + cacheSize);
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                int size = bitmap.getRowBytes() * bitmap.getHeight() / 1024;
                Log.e(TAG, "size =" + size);
                return size;
            }
        };
    }

    public Bitmap get(String url) {
        return mImageCache.get(url);
    }

    public void put(String url, Bitmap bmp) {
        mImageCache.put(url, bmp);
    }

    @Override
    public String toString() {
        return "使用的是内存缓存";
    }
}
