package com.example.eatsgo.api_setup.data_class

data class EatsGoResponse(
    val record: EatsGoAppData,
    val metadata: Metadata
)

data class EatsGoAppData(
    val restaurants: List<Restaurant>
)

data class Metadata(
    val id: String,
    val private: Boolean,
    val createdAt: String
)

