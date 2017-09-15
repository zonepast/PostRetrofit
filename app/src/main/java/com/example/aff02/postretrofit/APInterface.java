package com.example.aff02.postretrofit;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by AFF02 on 14-Sep-17.
 */

public interface APInterface {

    @POST("/posts")
    @FormUrlEncoded
    Call<DataModel> savePost(@Field("username") String username,
                             @Field("password") String password);
}
