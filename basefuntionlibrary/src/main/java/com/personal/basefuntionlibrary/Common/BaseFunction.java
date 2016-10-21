package com.personal.basefuntionlibrary.Common;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.personal.basefuntionlibrary.R;

/**
 * 功能相关的基础操作
 * Created by 杨培韬 on 2016/8/3.
 */
public class BaseFunction {
    /**
     * 手机号长度
     */
    public static final int PHONE_LENGTH = 11;
    /**
     * 按返回键退出的最大间隔时间
     */
    private static final long TIME_EXIT = 3 * 1000;
    /**
     * 记录按返回键的时间
     */
    private static long recordExitTime = 0;

    private BaseFunction() {
    }

    /**
     * 判断当前是否联网
     *
     * @param context 当前的环境
     * @return true已联网
     */
    public static boolean isNetWorkConnected(final Context context) {
        ConnectivityManager man = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = man.getActiveNetworkInfo();
        return (info != null) && (info.isAvailable());
    }

    public static boolean isGetOut(Context context) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - recordExitTime) > TIME_EXIT) {
            recordExitTime = currentTime;
            Toast.makeText(context, R.string.base_function_exit, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public static void getHome(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

}

