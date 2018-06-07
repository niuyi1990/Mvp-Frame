package com.niuyi.soft.mvpframe;

import com.niuyi.soft.library.mvp.MvpPresenter;
import com.niuyi.soft.library.mvp.MvpView;

import java.util.List;

/**
 * 描述：
 * 创建人：牛毅
 * 创建日期：2018/06/07 10：37
 */
public interface MainContract {

    interface View extends MvpView {

        void setRefresh(boolean enable);

        void showData(List<String> data);
    }

    interface Presenter extends MvpPresenter<View> {

        void getData();
    }
}
