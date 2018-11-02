package com.ivyzh.oopbasicprinciple.srp.simple5;

import java.util.Map;

import com.ivyzh.oopbasicprinciple.srp.simple5.frame.IRequestMethod;
import com.ivyzh.oopbasicprinciple.srp.simple5.frame.OkHttpRequstFrame;

/**
 * Created by Ivy on 2018/11/1.
 */

public class HttpUtils {
    private IRequestMethod mRequest;

    public HttpUtils() {
         mRequest = new OkHttpRequstFrame();//如果更改网络请求框架只需要这里修改
        // mRequest = new XUtilsRequstFrame();
        //mRequest = new VolleyRequstFrame();
    }


    public <T> void get(final String url, MyCallBack<T> callback, final boolean isCache) {
        mRequest.get(url, isCache, callback);
    }

    public <T> void post(final String url, MyCallBack<T> callback, final boolean isCache, Map<String, String> params) {
        mRequest.post(url, isCache, callback, params);
    }
}
