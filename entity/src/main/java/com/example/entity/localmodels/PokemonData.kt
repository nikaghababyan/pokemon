package com.example.entity.localmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemondata")
data class PokemonData(
    @PrimaryKey
    val id: Int = 100,
    @ColumnInfo(name = "count")
    val count: Int?,
    @ColumnInfo(name = "next")
    val next: String?,
    @ColumnInfo(name = "previous")
    val previous: String?,
    @ColumnInfo(name = "results")
    val results: List<PokemonItem>?
)