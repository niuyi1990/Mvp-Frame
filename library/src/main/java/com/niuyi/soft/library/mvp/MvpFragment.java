package com.niuyi.soft.library.mvp;

import android.support.annotation.StringRes;

import com.niuyi.soft.library.base.BaseFragment;
import com.niuyi.soft.library.utils.ToastUtils;

/**
 * 描述：
 * 创建人：牛毅
 * 创建日期：2018/06/07 10：14
 */
public abstract class MvpFragment<P extends MvpPresenter> extends BaseFragment implements MvpView {

    private P mPresenter;

    protected abstract P createPresenter();

    @Override
    public void showToast(String messgae) {
        ToastUtils.showToast(messgae);
    }

    @Override
    public void showToast(@StringRes int message) {
        ToastUtils.showToast(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destory();
            mPresenter = null;
        }
    }

    protected P getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
            mPresenter.attachView(this);
        }

        return mPresenter;
    }

}
