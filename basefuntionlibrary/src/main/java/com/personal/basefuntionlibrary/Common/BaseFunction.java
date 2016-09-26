package com.personal.basefuntionlibrary.Common;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.personal.basefuntionlibrary.R;
import com.personal.basefuntionlibrary.Util.PhoneUtil;

/**
 * Created by ustc on 2016/8/3.
 */
public class BaseFunction {
    /**
     * 手机号长度
     */
    public static final int PHONE_LENGTH = 11;
    /**
     * 登录密码的最小长度
     */
    private static final int MIN_PASS_LENGTH = 6;
    /**
     * 登录密码的最大长度
     */
    private static final int MAX_PASS_LENGTH = 20;
    /**
     * 验证码长度
     */
    private static final int VERIFY_CODE_LENGTH = 6;
    /**
     * 按返回键退出的最大间隔时间
     */
    private static final long TIME_EXIT = 3 * 1000;
    /**
     * 记录按返回键的时间
     */
    private static long recordExitTime = 0;

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
        boolean ret;
        if (info != null && info.isAvailable()) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
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

    public static boolean checkVerify(Context context, String verify) {
        if (verify.length() != VERIFY_CODE_LENGTH) {
            Toast.makeText(context, R.string.base_function_check_verify_code, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkPhone(Context context, String phone) {
        if (!PhoneUtil.isPhoneNumber(phone)) {
            Toast.makeText(context, R.string.base_function_check_phone, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkPass(Context context, String pass) {
        boolean ret = false;
        if (pass.length() >= MIN_PASS_LENGTH &&
                pass.length() <= MAX_PASS_LENGTH) {
            boolean haveNum = false;
            for (int i = pass.length(); --i >= 0; ) {
                char c = pass.charAt(i);
                if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                    ret = true;
                    break;
                }
            }
            for (int i = pass.length(); --i >= 0; ) {
                char c = pass.charAt(i);
                if (((c >= '0' && c <= '9'))) {
                    haveNum = true;
                    break;
                }
            }
            ret = ret && haveNum;
        }
        if (!ret) {
            Toast.makeText(context, R.string.base_function_check_pass, Toast.LENGTH_SHORT).show();
        }
        return ret;
    }

    public static boolean checkPass(Context context, String pass1, String pass2) {
        if (!pass1.equals(pass2)) {
            Toast.makeText(context, R.string.base_function_check_ensure_pass, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}

