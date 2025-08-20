package com.example.eatsgo.api_setup

import com.example.eatsgo.api_setup.data_class.EatsGoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("b/68a601c8ae596e708fcf3d19")
    suspend fun getRestaurants(): Response<EatsGoResponse>
}