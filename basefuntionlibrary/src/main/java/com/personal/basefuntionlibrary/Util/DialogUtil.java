package com.personal.basefuntionlibrary.Util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

/**
 * 自定义的对话框类
 * Created by 杨培韬 on 2016/8/15.
 */
public class DialogUtil extends Dialog {

    public DialogUtil(final Context context, final View view) {
        super(context);
        createUserDialog(view, true);
    }

    public DialogUtil(final Context context, final View view, final boolean isCancelable) {
        super(context);
        createUserDialog(view, isCancelable);


    }

    private void createUserDialog(View view, boolean isCancelable) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);
        getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);

        setCancelable(isCancelable);
        setCanceledOnTouchOutside(isCancelable);
    }

}