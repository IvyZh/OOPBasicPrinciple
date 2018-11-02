package com.ivyzh.oopbasicprinciple.srp.simple5.frame;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.ivyzh.oopbasicprinciple.srp.simple5.MyCallBack;
import com.ivyzh.oopbasicprinciple.srp.simple5.Utils;
import com.ivyzh.oopbasicprinciple.srp.simple5.cache.DiskCacheUtils;
import com.ivyzh.oopbasicprinciple.srp.simple5.cache.ICacheUtils;


/**
 * 采用OKHttp网络请求框架
 */

public class OkHttpRequstFrame implements IRequestMethod {
    private ICacheUtils mCacheUtils;

    public OkHttpRequstFrame() {
        mCacheUtils = new DiskCacheUtils();// 如果更改缓存策略只要改这里即可，app里面的代码不需要修改。
//        mCacheUtils = new DataBaseCacheUtils();
    }


    @Override
    public <T> void get(final String url, final boolean isCache, final MyCallBack<T> callBack) {
        if (isCache) {
            String result = mCacheUtils.get(url);
            if (!TextUtils.isEmpty(result)) {
                Log.e("OkHttp Cache:", mCacheUtils.toString());
                Gson gson = new Gson();
                T obj = (T) gson.fromJson(result, Utils.analysisClazzInfo(callBack));
                callBack.onSuccess(obj);
                return;
            }
        }

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                // 注意这个回调是在子线程
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 注意这个回调是在子线程
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    result = "{\n" +
                            "\t\"name\": \"NovaInNetOkHttp\",\n" +
                            "\t\"age\": 22\n" +
                            "}";
                    T obj = (T) new Gson().fromJson(result, Utils.analysisClazzInfo(callBack));
                    Log.e("OkHttp Cache:", "No Cache,Use NetData");
                    callBack.onSuccess(obj);
                    if (isCache)
                        mCacheUtils.set(url, result);// sp缓存替换成disk缓存

                } else {
                    final int code = response.code();
                    callBack.onError(code);

                }
            }
        });

    }

    @Override
    public void download(String url, String path) {

    }

    @Override
    public void upload(String url, File f, Map params) {

    }

    @Override
    public void post(String url, boolean isCache, MyCallBack callBack, Map params) {

    }
}
