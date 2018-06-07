package com.niuyi.soft.library.mvp;

import android.support.annotation.StringRes;

/**
 * 描述：
 * 创建人：牛毅
 * 创建日期：2018/06/07 09：39
 */
public interface MvpView {

    void showToast(String messgae);

    void showToast(@StringRes int message);

}
