package com.personal.basefuntionlibrary.Common;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.personal.basefuntionlibrary.R;

/**
 * 与界面操作相关的基础操作
 * Created by 杨培韬 on 2016/9/26.
 */
public class ViewFunction {

    public ViewFunction() {
    }

    public static void setCheckboxIcon(ImageView iv, boolean ret) {
        if (ret) {
            iv.setImageResource(R.mipmap.icon_checkbox_on);
        } else {
            iv.setImageResource(R.mipmap.icon_checkbox_off);
        }
    }

    public static boolean setSwitchIcon(ImageView iv, boolean ret) {
        if (ret) {
            iv.setImageResource(R.mipmap.icon_switch_on);
        } else {
            iv.setImageResource(R.mipmap.icon_switch_off);
        }
        return ret;
    }

    public static void creatDelImageViewClick(ImageView iv, final EditText et) {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
            }
        });
    }

    public static void setImgaeViewByEditText(EditText et, ImageView iv) {
        if (et.getText().toString().length() > 0) {
            iv.setVisibility(View.VISIBLE);
        } else {
            iv.setVisibility(View.GONE);
        }
    }

}
