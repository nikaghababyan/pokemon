package com.example.entity.responcemodel


import com.squareup.moshi.Json

data class StatResponse(
    @field:Json(name = "base_stat")
    val baseStat: Int?,
    @Json(name = "effort")
    val effort: Int?,
    @Json(name = "stat")
    val stat: StatItemResponse?
)