package com.niuyi.soft.library.http;

import com.niuyi.soft.library.bean.GitModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 名称: HttpService
 * 作者: 牛毅
 * 日期: 2018/6/7 15:31
 * 描述: 接口管理类
 */
public interface HttpService {

    String BASE_URL = "https://api.github.com";

    @GET("users/{user}")
    Call<String> getData(@Path("user") String user);

    @GET("users/{user}")
    Call<GitModel> getUserInfo(@Path("user") String user);

    @GET("users/{user}")
    Observable<GitModel> rxUser(@Path("user") String user);
}
