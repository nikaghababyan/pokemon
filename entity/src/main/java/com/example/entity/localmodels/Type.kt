package com.example.entity.localmodels


import com.squareup.moshi.Json

data class Type(
    @Json(name = "slot")
    val slot: Int?,
    @Json(name = "type")
    val type: TypeItem?
)