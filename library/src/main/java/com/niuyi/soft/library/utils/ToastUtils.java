package com.niuyi.soft.library.utils;

import android.widget.Toast;

import com.niuyi.soft.library.App;

/**
 * 描述：土司提示工具类
 * 创建人：牛毅
 * 创建日期：2018/06/07 10：31
 */
public class ToastUtils {

    private static Toast mToast;

    public static void showToast(String messgae) {
        if (mToast == null) {
            mToast = Toast.makeText(App.context(), messgae, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(messgae);
        }

        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showToast(int messgae) {
        if (mToast == null) {
            mToast = Toast.makeText(App.context(), messgae, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(messgae);
        }

        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showToastLong(String messgae) {
        if (mToast == null) {
            mToast = Toast.makeText(App.context(), messgae, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(messgae);
        }

        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showToastLong(int messgae) {
        if (mToast == null) {
            mToast = Toast.makeText(App.context(), messgae, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(messgae);
        }

        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }
}
