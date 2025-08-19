package com.example.eatsgo.api_setup

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val base_URL = "https://api.jsonbin.io/v3/"

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val eats_go_api: ApiInterface = getRetrofitInstance().create(ApiInterface::class.java)
}