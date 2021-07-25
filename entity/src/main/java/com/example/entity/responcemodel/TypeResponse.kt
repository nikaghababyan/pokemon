package com.example.entity.responcemodel


import com.squareup.moshi.Json

data class TypeResponse(
    @Json(name = "slot")
    val slot: Int?,
    @Json(name = "type")
    val type: TypeItemResponse?
)