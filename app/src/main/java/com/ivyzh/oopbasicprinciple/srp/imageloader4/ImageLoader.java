package com.ivyzh.oopbasicprinciple.srp.imageloader4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Ivy on 2018/11/2.
 */

public class ImageLoader {
    private String TAG = "ImageLoader";
    private static ImageLoader mInstance;
    private static ImageCache mImageCache;
    // 线程池，线程数量为CPU的数量
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                    mImageCache = new ImageCache();
                }
            }
        }
        return mInstance;
    }

    private ImageLoader() {
    }

    public void display(final String url, final ImageView imageView, final Handler handler) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null) {
            Message msg = Message.obtain();
            msg.what = 1;
            msg.obj = bitmap;
            handler.sendMessage(msg);
            // imageView.setImageBitmap(bitmap); 这样设置的话，图片不显示
            Log.e(TAG, "缓存中有数据，读取缓存中数据");
            return;
        }
        imageView.setTag(url);//此处设置Tag是解决图片错乱问题

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap != null) {
                    if (imageView.getTag().equals(url)) {//对比当前下载的图片是否是正对应ImageView
                        Log.e(TAG, "缓存中没有数据，网上下载，然后存入缓存中" + bitmap.toString());
                        String name = Thread.currentThread().getName();
                        Log.e(TAG, "name:" + name);
                        //imageView.setImageBitmap(bitmap); 这样设置的话，图片不显示
                        Message msg = Message.obtain();
                        msg.what = 2;
                        msg.obj = bitmap;
                        handler.sendMessage(msg);

                        mImageCache.put(url, bitmap);
                        Bitmap bmp = mImageCache.get(url);
                        Log.e(TAG, "缓存中bmp" + bmp.toString());
                    }
                }
            }
        });
    }

    private Bitmap downloadImage(String imgUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;

    }
}
