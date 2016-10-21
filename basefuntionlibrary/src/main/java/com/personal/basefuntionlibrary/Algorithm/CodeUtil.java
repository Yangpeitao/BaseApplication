package com.personal.basefuntionlibrary.Algorithm;

import android.util.Base64;

/**
 * 编码基础类
 * Created by 杨培韬 on 2016/10/21.
 */

public class CodeUtil {

    private CodeUtil() {
    }

    public static String encode(final byte[] input) {

        return Base64TransUtil.encode(input);
    }

    public static byte[] decode(final String input) {

        return Base64TransUtil.decode(input);
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
