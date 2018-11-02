package com.ivyzh.oopbasicprinciple.srp.imageloader2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivyzh.oopbasicprinciple.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    TextView mTextView;
    private String TAG = "MainActivity";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Bitmap bmp = (Bitmap) msg.obj;
                mImageView.setImageBitmap(bmp);
                mTextView.setText("cache");
            } else if (msg.what == 2) {
                Bitmap bmp = (Bitmap) msg.obj;
                mImageView.setImageBitmap(bmp);
                mTextView.setText("net");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        mImageView = findViewById(R.id.iv_result);
        mTextView = findViewById(R.id.tv_from);
    }

    public void request(View v) {
        String name = Thread.currentThread().getName();
        Log.e(TAG, "name:" + name);
        String imgUrl = "https://cdn2.jianshu.io/assets/default_avatar/2-9636b13945b9ccf345bc98d0d81074eb.jpg";
        // 如果用https的url，则会报错 java.net.UnknownHostException: Unable to resolve host
        imgUrl = "http://pic19.nipic.com/20120210/7827303_221233267358_2.jpg";
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.display(imgUrl, mImageView, mHandler);
    }
}
