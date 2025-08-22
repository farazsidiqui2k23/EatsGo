package com.example.eatsgo.api_setup.data_class

data class TopDeal(
    val name: String,
    val pic: String,
    val item: String,
    val desc: String,
    val discount: String,
    val tagline: String,
    val price: Int = 2400
)
