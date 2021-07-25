package com.example.entity.responcemodel

import com.squareup.moshi.Json

data class PokemonDataResponse(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val results: List<PokemonListResponse>?
)