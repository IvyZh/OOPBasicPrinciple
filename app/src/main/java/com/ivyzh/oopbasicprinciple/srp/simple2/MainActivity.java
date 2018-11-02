package com.ivyzh.oopbasicprinciple.srp.simple2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ivyzh.oopbasicprinciple.R;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv_result);
    }

    public void request(View v) {
        String url = "https://www.baidu.com/";

        HttpUtils.get(url, new MyCallBack<String>() {
            @Override
            public void onSuccess(final String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("success HttpUtils:" + result);
                    }
                });
            }

            @Override
            public void onFailure(final Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("failure HttpUtils:" + e.getMessage());
                    }
                });
            }

            @Override
            public void onError(final int errorCode) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("error code HttpUtils:" + errorCode);
                    }
                });
            }
        });


    }
}
