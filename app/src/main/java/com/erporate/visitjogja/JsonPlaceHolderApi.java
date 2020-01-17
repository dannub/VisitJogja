package com.erporate.visitjogja;


import com.erporate.visitjogja.Api.GetWisata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("jsonBootcamp.php")
    Call<GetWisata> getWisata();
}
