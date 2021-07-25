package com.example.entity.localmodels


import com.squareup.moshi.Json

data class TypeItem(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)