package com.personal.basefuntionlibrary.Common;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.personal.basefuntionlibrary.R;
import com.personal.basefuntionlibrary.Util.PhoneUtil;


/**
 * 与界面操作相关的基础操作
 * Created by 杨培韬 on 2016/9/26.
 */
public class ViewFunction {

    private ViewFunction() {
    }

    public static void setBinaryIcon(ImageView iv, boolean ret, int resIdOn, int resIdOff) {
        if (ret) {
            iv.setImageResource(resIdOn);
        } else {
            iv.setImageResource(resIdOff);
        }
    }

    public static void createDelImageViewClick(ImageView iv, final EditText et) {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
            }
        });
    }

    public static void setImageViewByEditText(EditText et, ImageView iv) {
        if (et.getText().toString().length() > 0) {
            iv.setVisibility(View.VISIBLE);
        } else {
            iv.setVisibility(View.GONE);
        }
    }

    public static boolean checkVerify(Context context, String verify) {
        if (verify.length() != BaseConstant.VERIFY_CODE_LENGTH) {
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
        if (pass.length() >= BaseConstant.MIN_PASS_LENGTH &&
                pass.length() <= BaseConstant.MAX_PASS_LENGTH) {
            ret = BaseFunction.isAlphabetAndNumber(pass);
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
