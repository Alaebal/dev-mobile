package com.projet;

import com.example.myapplication.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface app {
    @GET("search.php")
    Call<MealResponse> searchByName(@Query("s") String name);

    @GET("filter.php")
    Call<MealResponse> filterByIngredient(@Query("i") String ingredient);

    @GET("lookup.php")
    Call<MealResponse> getById(@Query("i") String id);
}