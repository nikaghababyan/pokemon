package com.example.entity.responcemodel


import com.squareup.moshi.Json

data class TypeItemResponse(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)