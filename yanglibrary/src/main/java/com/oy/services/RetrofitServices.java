package com.oy.services;


import com.oy.util.InstanceUtil;

import org.json.JSONObject;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


/**
 * Created by Administrator on 2016/10/24.
 */
public interface RetrofitServices {
    @GET()
    Call<ResponseBody> getJSON(@Url String url);
    @POST(InstanceUtil.user_reg)
    Call<ResponseBody> createUser(@Body JSONObject jsonObject);
    @POST(InstanceUtil.user_login)
    Call<ResponseBody> userLogin(@Body JSONObject jsonObject);
}
