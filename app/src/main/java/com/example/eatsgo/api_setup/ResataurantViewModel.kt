package com.example.eatsgo.api_setup

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatsgo.api_setup.data_class.EatsGoResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResataurantViewModel : ViewModel() {

    val eatsGoAPI = RetrofitInstance.eats_go_api

    private val _restaurantState =
        MutableStateFlow<RestaurantDataState<EatsGoResponse>>(RestaurantDataState.onLoading)
    val restaurantState: StateFlow<RestaurantDataState<EatsGoResponse>> = _restaurantState

    fun getRestaurantsData() {
        viewModelScope.launch {
            try {
                val response = eatsGoAPI.getRestaurants()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _restaurantState.value = RestaurantDataState.onSuccess(it)
                    }
                } else {
                    _restaurantState.value = RestaurantDataState.onFailure(response.message())
                }
            } catch (e: Exception) {
                _restaurantState.value = RestaurantDataState.onFailure(e.message.toString())

            }
        }
    }
}

sealed class RestaurantDataState<out T> {
    data class onSuccess<out T>(val data: T) : RestaurantDataState<T>()
    data class onFailure(val message: String) : RestaurantDataState<Nothing>()
    object onLoading : RestaurantDataState<Nothing>()
}