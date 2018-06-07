package com.niuyi.soft.library;

import android.app.Application;

/**
 * 描述：
 * 创建人：牛毅
 * 创建日期：2018/06/07 10：33
 */
public class App extends Application {

    private static App mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static App context() {
        return mContext;
    }
}
