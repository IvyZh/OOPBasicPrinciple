package com.ivyzh.oopbasicprinciple.srp.imageloader3.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 磁盘缓存
 */

public class DiskImageCache implements IImageCache {
    private String TAG = "DiskImageCache";
    //    private String cacheDir = "sdcard/cache/";
    private String cacheDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

    public DiskImageCache() {
    }


    @Override
    public Bitmap get(String url) {
        String fileName = fixUrl(url);
        return BitmapFactory.decodeFile(cacheDir + fileName);
    }


    @Override
    public void put(String url, Bitmap bmp) {
        String fileName = fixUrl(url);
        FileOutputStream fos = null;
        try {
            File file = new File(cacheDir + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Log.e(TAG, "存在了磁盘上面");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                fos = null;
                e.printStackTrace();
            }
        }

    }

    private String fixUrl(String url) {
        return "2018.png";  //根据url生成一个文件名，可以是md5或者图片名称。这里简单处理，返回固定的值。
    }

    @Override
    public String toString() {
        return "使用的是磁盘缓存";
    }
}
