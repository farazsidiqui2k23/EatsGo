package com.example.eatsgo.api_setup

import com.example.eatsgo.api_setup.data_class.EatsGoAppData
import com.example.eatsgo.api_setup.data_class.EatsGoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @GET("b/68a3fca9d0ea881f405d0cdd")
    suspend fun getRestaurants(): Response<EatsGoResponse>
}