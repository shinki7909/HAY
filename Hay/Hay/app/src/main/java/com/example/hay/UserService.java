package com.example.hay;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
        public static final String USER_URL= "http://192.168.0.125:4000/";


    @POST("user/insert")
    Call<Void> insertappuser(@Body AppuserVO vo);

    @GET("user/checkid")
    Call<Integer> checkid(@Query("userid") String userid);



}
