package com.niuyi.soft.mvpframe;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.niuyi.soft.library.bean.GitModel;
import com.niuyi.soft.library.http.HttpService;
import com.niuyi.soft.library.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends MvpActivity<MainContract.Presenter> implements MainContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSfMain;
    private RecyclerView mRvMian;

    private List<String> mData = new ArrayList<>();
    private MainAdapter mMainAdapter;

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mSfMain = (SwipeRefreshLayout) findViewById(R.id.sf_main);
        mRvMian = (RecyclerView) findViewById(R.id.rv_mian);

        mRvMian.setLayoutManager(new LinearLayoutManager(this));
        mMainAdapter = new MainAdapter(this, mData);
        mRvMian.setAdapter(mMainAdapter);
    }

    @Override
    protected void initListener() {
        mSfMain.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
//        getPresenter().getData();

        loadData();
    }

    private void loadData() {
//        getString();

//        getJson();

        rxJson();
    }

    private void rxJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        HttpService service = retrofit.create(HttpService.class);
        Observable<GitModel> obserable = service.rxUser("niuyi1990");
        obserable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GitModel>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(GitModel gitModel) {
                        Log.i(TAG, "onNext: " + gitModel.getAvatarUrl());
                    }
                });
    }

    private void getString() {
        //请求String
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpService.BASE_URL)
                //添加String支持
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        HttpService service = retrofit.create(HttpService.class);
        Call<String> call = service.getData("niuyi1990");//username 可以自己传入github的用户名

        // 异步请求
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                // 处理返回数据
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i(TAG, "onFailure: 请求数据失败");
            }
        });
    }

    private void getJson() {
        //请求json
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpService.BASE_URL)
                // 添加Json转换器支持
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpService service = retrofit.create(HttpService.class);
        Call<GitModel> call = service.getUserInfo("niuyi1990");//username 可填入自己Github账号用户名
        call.enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.body().getAvatarUrl());
                }
            }

            @Override
            public void onFailure(Call<GitModel> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    protected void setOnLick(View view) {

    }

    @Override
    public void setRefresh(boolean enable) {
        mSfMain.setRefreshing(enable);
    }

    @Override
    public void showData(List<String> data) {
        mData.clear();
        mData.addAll(data);
        mMainAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        getPresenter().getData();
    }
}
