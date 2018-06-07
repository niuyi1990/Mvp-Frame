package com.niuyi.soft.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描述：封装Fragment基类
 * 创建人：牛毅
 * 创建日期：2018/06/07 09：12
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private boolean mIsViewPrepared = false;//标记视图是否加载完毕

    private boolean mHasLoadData = false;//标记是否加载过数据

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutId(), container, false);

        initView(view);
        initListener();
        initData();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewPrepared = true;
        lazyLoadDataPrepared();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoadDataPrepared();
        }
    }

    private void lazyLoadDataPrepared() {
        if (getUserVisibleHint() && mIsViewPrepared && !mHasLoadData) {
            //视图可见 && 视图加载完毕 && 没有加载过数据
            lazyLoadData();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                setOnLick(view);
                break;
        }
    }

    protected abstract int setLayoutId();

    protected abstract void initView(View view);

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void setOnLick(View view);

    protected void lazyLoadData() {

    }

}
