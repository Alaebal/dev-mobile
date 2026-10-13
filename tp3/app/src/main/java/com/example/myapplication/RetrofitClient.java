package com.example.myapplication;

import com.projet.app;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static app api;

    public static app getApi() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(app.class);
        }
        return api;
    }
}
