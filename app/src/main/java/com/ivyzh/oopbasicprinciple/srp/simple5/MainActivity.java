package com.ivyzh.oopbasicprinciple.srp.simple5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ivyzh.oopbasicprinciple.R;

import com.ivyzh.oopbasicprinciple.srp.simple5.domain.User;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;

    boolean isCache = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_plus);
        mTextView = findViewById(R.id.tv_result);
    }

    // OkHttp cache:sp
    public void request(View v) {
        isCache = true;
        loadData();
    }

    // OkHttp cache:disk
    public void request2(View v) {
        isCache = true;
        loadData();// 注意，这里其实表示如果在更改缓存策略的时候其实可以不需要修改app的代码
    }

    // OkHttp cache:no
    public void request3(View v) {
        isCache = false;
        loadData();// 注意，这里其实表示如果在更改缓存策略的时候其实可以不需要修改app的代码
    }

    // Xutils cache:sp
    public void request4(View v) {
        isCache = true;
        loadData();
    }

    // Xutils cache:disk
    public void request5(View v) {
        isCache = true;
        loadData();// 注意，这里其实表示如果在更改缓存策略的时候其实可以不需要修改app的代码
    }

    // Xutils cache:no
    public void request6(View v) {
        isCache = false;
        loadData();// 注意，这里其实表示如果在更改缓存策略的时候其实可以不需要修改app的代码
    }


    private void loadData() {
        String url = "https://www.baidu.com/";
        HttpUtils utils = new HttpUtils();//其实可以做成静态
        utils.get(url, new MyCallBack<User>() {
            @Override
            public void onSuccess(final User result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("success :" + result);
                    }
                });
            }

            @Override
            public void onFailure(final Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("failure:" + e.getMessage());
                    }
                });
            }

            @Override
            public void onError(final int errorCode) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("error code:" + errorCode);
                    }
                });
            }
        }, isCache);
    }
}
