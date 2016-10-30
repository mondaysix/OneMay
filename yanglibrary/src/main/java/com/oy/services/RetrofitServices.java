package com.oy.services;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface RetrofitServices {
    @GET()
    Call<ResponseBody> getJSON(@Url String url);
}
