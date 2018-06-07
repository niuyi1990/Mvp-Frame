package com.niuyi.soft.library.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

/**
 * 描述：
 * 创建人：牛毅
 * 创建日期：2018/06/07 09：39
 */
public interface MvpPresenter<V extends MvpView> {

    @UiThread
    void start();

    @UiThread
    void attachView(@NonNull V view);

    @UiThread
    void detachView();

    @UiThread
    void destory();
}
