package com.example.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entity.localmodels.PokemonData
@Dao
interface PokemonListDto {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDefaultData(item: PokemonData)

    @Query("SELECT * FROM pokemondata")
    fun getPokemonDataList(): PokemonData

}