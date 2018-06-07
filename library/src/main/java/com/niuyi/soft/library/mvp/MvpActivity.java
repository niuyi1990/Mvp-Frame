package com.niuyi.soft.library.mvp;

import android.support.annotation.StringRes;

import com.niuyi.soft.library.base.BaseActivity;
import com.niuyi.soft.library.utils.ToastUtils;

/**
 * 描述：
 * 创建人：牛毅
 * 创建日期：2018/06/07 09：38
 */
public abstract class MvpActivity<P extends MvpPresenter> extends BaseActivity implements MvpView {

    private P mPresenter;

    @Override
    public void showToast(String messgae) {
        ToastUtils.showToast(messgae);
    }

    @Override
    public void showToast(@StringRes int messgae) {
        ToastUtils.showToast(messgae);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destory();
            mPresenter = null;
        }
    }

    protected abstract P createPresenter();

    protected P getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
            mPresenter.attachView(this);
        }

        return mPresenter;
    }

}
