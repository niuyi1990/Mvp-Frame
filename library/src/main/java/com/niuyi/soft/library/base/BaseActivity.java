package com.niuyi.soft.library.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 描述：封装Activity基类
 * 创建人：牛毅
 * 创建日期：2018/06/07 09：07
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(View.inflate(this, setLayoutId(), null));

        initView();
        initListener();
        initData();
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

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void setOnLick(View view);
}
