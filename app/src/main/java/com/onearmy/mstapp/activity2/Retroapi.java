package com.onearmy.mstapp.activity2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Retroapi {
    public  static String BASE_DIRI = "https://jsonplaceholder.typicode.com/";

    @GET("posts")
    Call<List<RetroModel>> getJasonList();
}
