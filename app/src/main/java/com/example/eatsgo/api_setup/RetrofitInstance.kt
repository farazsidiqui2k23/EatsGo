package com.example.eatsgo.api_setup

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val base_URL = "https://api.jsonbin.io/v3/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-Master-Key", XMasterKey.MASTER_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val eats_go_api: ApiInterface = getRetrofitInstance().create(ApiInterface::class.java)
}