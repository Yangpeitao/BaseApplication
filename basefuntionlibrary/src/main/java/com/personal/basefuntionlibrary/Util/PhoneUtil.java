package com.personal.basefuntionlibrary.Util;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;

import com.personal.basefuntionlibrary.Common.BaseConstant;
import com.personal.basefuntionlibrary.Common.BaseFunction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号格式的判断类
 * Created by 杨培韬 on 2016/8/4.
 */
public class PhoneUtil {

    private PhoneUtil() {
    }

    public static boolean isPhoneNumber(final String formatPhone) {
        if (TextUtils.isEmpty(formatPhone)) {
            return false;
        }
        if (formatPhone.length() == BaseConstant.PHONE_LENGTH) {
            for (int i = 0; i < BaseConstant.PHONE_LENGTH; i++) {
                if (!PhoneNumberUtils.isISODigit(formatPhone.charAt(i))) {
                    return false;
                }
            }
            Pattern p = Pattern.compile("^((13[^4,\\D])" + "|(134[^9,\\D])" +
                    "|(14[5,7])" +
                    "|(15[^4,\\D])" +
                    "|(17[3,6-8])" +
                    "|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(formatPhone);
            return m.matches();
        }
        return false;
    }

    /**
     * 规范化电话号码. 去掉12502 +86等前缀.
     *
     * @param phone 电话号码.
     * @return 格式化后的电话号码
     */
    public static String formatPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return phone;
        }
        if (phone.startsWith("+86")) {
            phone = phone.substring("+86".length());
        } else if (phone.startsWith("12520")) {
            phone = phone.substring("12520".length());
        }
        //去除空白字符或'-'，有的通讯录获取的号码之间有space 或'-';
        phone = phone.replaceAll("[\\s-]", "");
        return phone;
    }
}

