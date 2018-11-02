package com.ivyzh.oopbasicprinciple.isp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ivyzh.oopbasicprinciple.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 接口隔离原则 ISP interface Segregation Principle
 */
public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    String cacheDir = "sdcard/cache/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        mImageView = findViewById(R.id.iv_result);

    }

    public void request(View v) {
        String url = "";
        Bitmap bmp = null;
        put(url, bmp);
        put2(url, bmp);
    }

    private void put2(String url, Bitmap bmp) {
        String fileName = fixUrl(url);
        FileOutputStream fos = null;
        try {
            File file = new File(cacheDir + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.close(fos);
        }
    }

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
        } catch (Exception e) {
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
        return "2018.png";
    }

}
