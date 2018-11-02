package com.ivyzh.oopbasicprinciple.srp.simple5.frame;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.Map;

import com.ivyzh.oopbasicprinciple.srp.simple5.MyCallBack;
import com.ivyzh.oopbasicprinciple.srp.simple5.Utils;
import com.ivyzh.oopbasicprinciple.srp.simple5.cache.DiskCacheUtils;
import com.ivyzh.oopbasicprinciple.srp.simple5.cache.ICacheUtils;


/**
 * 采用Xutils网络请求框架
 */

public class XUtilsRequstFrame implements IRequestMethod {

    private ICacheUtils mCacheUtils;

    public XUtilsRequstFrame() {
        mCacheUtils = new DiskCacheUtils();// 如果更改缓存策略只要改这里即可，app里面的代码不需要修改。
    }

    @Override
    public <T> void get(final String url, final boolean isCache, final MyCallBack<T> callBack) {

        if (isCache) {
            String result = mCacheUtils.get(url);
            if (!TextUtils.isEmpty(result)) {
                Log.e("Xutils Cache:", mCacheUtils.toString());
                Gson gson = new Gson();
                T obj = (T) gson.fromJson(result, Utils.analysisClazzInfo(callBack));
                callBack.onSuccess(obj);
                return;
            }
        }

        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("wd", "xUtils");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                result = "{\n" +
                        "\t\"name\": \"NovaInNetXutils\",\n" +
                        "\t\"age\": 22\n" +
                        "}";
                T obj = (T) new Gson().fromJson(result, Utils.analysisClazzInfo(callBack));
                Log.e("Xutils Cache:", "No Cache,Use NetData");
                callBack.onSuccess(obj);
                if (isCache)
                    mCacheUtils.set(url, result);// sp缓存替换成disk缓存
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });

    }

    @Override
    public <T> void post(String url, boolean isCache, MyCallBack<T> callBack, Map<String, String> params) {

    }

    @Override
    public void upload(String url, File f, Map<String, String> params) {

    }

    @Override
    public void download(String url, String path) {

    }
}
