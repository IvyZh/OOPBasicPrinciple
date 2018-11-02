package com.ivyzh.oopbasicprinciple.isp;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Ivy on 2018/11/2.
 */

public class CloseUtils {
    private CloseUtils() {
    }

    public static void close(Closeable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
