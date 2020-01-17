package com.erporate.visitjogja;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erporate.visitjogja.Activity.MainActivity;
import com.erporate.visitjogja.Adapter.WisataAdapter;
import com.erporate.visitjogja.Api.GetWisata;
import com.erporate.visitjogja.Model.Wisata;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DBqueries {
    public static List<Wisata> wisataList = new ArrayList<>();
    public static String baseUrl = "http://erporate.com/bootcamp/";

    public static void loadWisata (final Context context, final Dialog loadingDialog, final RecyclerView recyclerViewWisata, final LinearLayoutManager linearLayoutManager){


        loadingDialog.show();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<GetWisata> call = jsonPlaceHolderApi.getWisata();

        call.enqueue(new Callback<GetWisata>() {
            @Override
            public void onResponse(Call<GetWisata> call, Response<GetWisata> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context,response.code(),Toast.LENGTH_SHORT).show();
                    return;

                }

                wisataList = response.body().getListDataWisata();



                Log.i("hasil",wisataList.toString());
                MainActivity.wisataAdapter = new WisataAdapter(DBqueries.wisataList);
                recyclerViewWisata.setAdapter(MainActivity.wisataAdapter);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewWisata.setLayoutManager(linearLayoutManager);
                MainActivity.wisataAdapter.notifyDataSetChanged();
                loadingDialog.dismiss();

            }

            @Override
            public void onFailure(Call<GetWisata> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });
    }
}
