package com.niuyi.soft.library.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

/**
 * 描述：封装Presenter基类
 * 创建人：牛毅
 * 创建日期：2018/06/07 10：18
 */
public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> mViewRef;

    @UiThread
    @Override
    public void start() {

    }

    @UiThread
    @Override
    public void attachView(@NonNull V view) {
        mViewRef = new WeakReference<V>(view);
    }

    @UiThread
    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    @UiThread
    @Override
    public void destory() {
        if (isViewAttached()) {
            detachView();
        }
    }

    @UiThread
    @NonNull
    public V getView() {
        return mViewRef == null ? null : mViewRef.get();
    }

    /**
     * 处理业务逻辑之前，先调用该方法，检查是否与View还建立连接，否则可能会报空指针异常
     *
     * @return
     */
    @UiThread
    public final boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }
}
