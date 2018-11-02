package com.ivyzh.oopbasicprinciple.srp.simple3;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ivy on 2018/11/1.
 */

public class HttpUtils {

    private HttpUtils() {
    }

    private static final OkHttpClient client;

    static {
        client = new OkHttpClient();
    }

    public static void get(final String url, final MyCallBack callback, final boolean isCache) {

        if (isCache) {
            String result = SharePreCacheUtils.get(url);
            if (!TextUtils.isEmpty(result)) {
                callback.onSuccess("tag:from cache," + result);
                return;
            }
        }


        Request request = new Request.Builder()
                .url(url)
                .get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                // 注意这个回调是在子线程
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 注意这个回调是在子线程
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    callback.onSuccess(result);
                    if (isCache)
                        SharePreCacheUtils.set(url, result);

                } else {
                    final int code = response.code();
                    callback.onError(code);

                }
            }
        });
    }
}
