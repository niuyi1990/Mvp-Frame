package com.niuyi.soft.mvpframe;

import android.os.Handler;

import com.niuyi.soft.library.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 创建人：牛毅
 * 创建日期：2018/06/07 10：38
 */
public class MainPresenter extends MvpBasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void start() {
        super.start();
        //这里可以进行相关初始化操作
    }

    @Override
    public void getData() {
        //模拟网络请求

        final List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        list.add("666");
        list.add("777");
        list.add("888");
        list.add("999");
        list.add("000");

        getView().setRefresh(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isViewAttached()) {
                    getView().setRefresh(false);
                    getView().showData(list);
                }
            }
        }, 3000);

    }
}
