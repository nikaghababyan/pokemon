package com.example.data.dataservice.sqlservice

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.entity.localmodels.PokemonData
import com.example.entity.responcemodel.PokemonDataResponse

@Database(
    entities = [PokemonData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonListDao():PokemonListDto
}