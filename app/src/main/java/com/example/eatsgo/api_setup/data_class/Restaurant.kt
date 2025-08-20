package com.example.eatsgo.api_setup.data_class

data class Restaurant(
    val name: String,
    val famous_for: String,
    val popular_pic: String,
    val items: List<Item>,
    val deals: List<Deal>,
    val location: String,
    val rating: Double,
    val has_top_deal: Boolean,
    val top_deal: TopDeal
)