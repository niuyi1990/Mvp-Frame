package com.niuyi.soft.library.http;

/**
 * 描述：Okhttp3 + Retrofit2 + Rxjava封装网络请求框架
 * 创建人：牛毅
 * 创建日期：2018/06/07 14：10
 */
public class Http {

    private static Http ourInstance;

    public static Http getHttpService() {
        if (ourInstance == null) {
            ourInstance = new Http();
        }
        return ourInstance;
    }

}
