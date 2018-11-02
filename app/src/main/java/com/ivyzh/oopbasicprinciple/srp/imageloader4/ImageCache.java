package com.ivyzh.oopbasicprinciple.srp.imageloader4;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * 主要负责图片缓存的逻辑
 */

public class ImageCache {
    private String TAG = "ImageCache";
    private LruCache<String, Bitmap> mImageCache;

    public ImageCache() {
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

    public Bitmap put(String url, Bitmap bmp) {
        return mImageCache.put(url, bmp);
    }
}
