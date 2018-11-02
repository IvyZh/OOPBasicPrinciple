package com.ivyzh.oopbasicprinciple.srp;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by Ivy on 2018/11/1.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 在application的onCreate中初始化
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
