package com.example.entity.responcemodel


import com.squareup.moshi.Json

data class PokemonInfoResponse(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "weight")
    val weight: Int?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "stats")
    val stats: List<StatResponse>?,
    @Json(name = "types")
    val types: List<TypeResponse>?

)