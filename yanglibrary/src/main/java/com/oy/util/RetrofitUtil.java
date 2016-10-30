package com.oy.util;

import android.util.Log;

import com.oy.services.RetrofitServices;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/10/24.
 */
public class RetrofitUtil {
    public Retrofit retrofit;
    public OkHttpClient okHttpClient;
    public OnGetJsonListener listener;
    public RetrofitUtil init(String base_url){
        okHttpClient = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .build();
        return this;
    }
    public RetrofitUtil setListener(OnGetJsonListener listener){
        this.listener = listener;
        return this;
    }
    public RetrofitUtil downData(String json_url){
        final String[] result = {null};
        RetrofitServices retrofitServices = retrofit.create(RetrofitServices.class);
        Call<ResponseBody> call = retrofitServices.getJSON(json_url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    result[0] = response.body().string();
                    listener.getJson(result[0]);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return this;
    }
    public interface OnGetJsonListener{
        void getJson(String json);

    }
}
