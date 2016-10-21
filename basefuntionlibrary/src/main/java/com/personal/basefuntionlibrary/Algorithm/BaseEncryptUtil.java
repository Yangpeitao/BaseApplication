package com.personal.basefuntionlibrary.Algorithm;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;

import org.ipc.crypto.AESCipher;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 加密相关的基础类
 * Created by 杨培韬 on 2016/10/21.
 */

public class BaseEncryptUtil {

    private static final int SDK_VERSION = Build.VERSION.SDK_INT;

    private static final int BASE64_BASE = 4;

    private BaseEncryptUtil() {
    }

    public static String encrypt(String input, String key) {
        try {
            byte[] bytes = AESCipher.encrypt(key.getBytes(), input.getBytes(), SDK_VERSION);
            return CodeUtil.encode(bytes);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String decrypt(String input, String key) {
        byte[] bytes = CodeUtil.decode(input);
        try {
            return new String(AESCipher.decrypt(key.getBytes(), bytes, SDK_VERSION));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getMd5String(String input) {
        byte[] bytes = new byte[0];
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            digester.update(input.getBytes());
            bytes = digester.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return CodeUtil.encode(bytes);
    }

    public static String getFormatMd5String(String input) {
        input = getMd5String(input);
        return input.replaceAll("[\\*\\?'<>\\|\\\\/;]", "");
    }

    public static boolean isBase64(final String input) {
        if (TextUtils.isEmpty(input)) {
            return false;
        }
        String str = input.replace("\n", "");
        if ((str.length() % BASE64_BASE) != 0) {
            return false;
        }
        if (input.indexOf('_') != -1) {
            return false;
        }
        // 表达式中含_也匹配
        String expression = "^[a-zA-z0-9+/\n]+=?=?$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private static class CodeUtil {

        private static String encode(final byte[] input) {

            return Base64TransUtil.encode(input);
        }

        private static byte[] decode(final String input) {

            return Base64TransUtil.decode(input);
        }
    }

    private static class Base64TransUtil {

        private static String encode(final byte[] input) {
            return Base64.encodeToString(input, Base64.NO_WRAP);
        }

        private static byte[] decode(final String inputString) {
            return Base64.decode(inputString, Base64.NO_WRAP);
        }

    }
}
