package com.ivyzh.oopbasicprinciple.srp.imageloader3.cache;

import android.graphics.Bitmap;

/**
 * 内存缓存+磁盘缓存
 */

public class DoubleImageCache implements IImageCache {
    private String TAG = "DoubleImageCache";
    DiskImageCache mDiskImageCache = new DiskImageCache();
    MemoryImageCache mMemoryImageCache = new MemoryImageCache();

    public DoubleImageCache() {

    }

    @Override
    public Bitmap get(String url) {
        Bitmap bmp = mMemoryImageCache.get(url);
        if (bmp != null) {
            return bmp;
        } else {
            bmp = mDiskImageCache.get(url);
            if (bmp != null) {
                return bmp;
            }
            // 内存和磁盘上都没有，则开线程下载，但是下载任务又不是在ImageCache的逻辑范畴之内，
            // 所以直接返回null即可，然后由ImageLoader自行判断

        }

        return null;
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mDiskImageCache.put(url, bmp);
        mMemoryImageCache.put(url, bmp);
    }
    @Override
    public String toString() {
        return "使用的是磁盘+内存缓存";
    }
}
