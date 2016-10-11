package com.personal.basefuntionlibrary.Util;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * 将String转化为拼音的类
 * Created by 杨培韬 on 2015/12/15.
 */
public class PinYinUtil {
    /**
     * 防止实例化.
     */
    private PinYinUtil() {
    }

    /**
     * 将汉字转换为对应的拼音 输入为"" 时输出也为"".
     *
     * @param inputString 汉字输入
     * @return 转换完成的拼音 输入为null 或"" 时 返回""
     */
    public static String getPingYin(final String inputString) {
        if (TextUtils.isEmpty(inputString)) {
            return "";
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        // 把字符串转化成字符数组
        char[] input = inputString.trim().toCharArray();
        String output = "";

        try {
            for (int i = 0; i < input.length; i++) {
                String[] temp = PinyinHelper.toHanyuPinyinStringArray(
                        input[i], format);
                // 取拼音的全部内容
                output += temp[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
            return inputString.toLowerCase();
        }
        return output;
    }
}

