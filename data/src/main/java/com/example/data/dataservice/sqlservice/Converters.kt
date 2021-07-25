package com.example.data.dataservice.sqlservice

import androidx.room.TypeConverter
import com.example.entity.localmodels.PokemonItem
import com.example.entity.localmodels.Stat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun fromPokemonItemData(json: String): PokemonItem? {

        val jsonAdapter: JsonAdapter<PokemonItem> = Moshi.Builder().build().adapter(PokemonItem::class.java)
        return jsonAdapter.fromJson(json)
    }

    @TypeConverter
    fun fromPokemonItemData(loginUserData: PokemonItem?): String? {

        val jsonAdapter: JsonAdapter<PokemonItem> = Moshi.Builder().build().adapter(PokemonItem::class.java)
        return jsonAdapter.toJson(loginUserData)
    }
    @TypeConverter
    fun fromPokemonItemDbData(json: String): List<PokemonItem>? {
        val listMyData: Type = Types.newParameterizedType(MutableList::class.java, PokemonItem::class.java)
        val adapter: JsonAdapter<List<PokemonItem>> = Moshi.Builder().build().adapter(listMyData)
        return adapter.fromJson(json);
    }

    @TypeConverter
    fun fromPokemonItemDbData(images: List<PokemonItem>): String {
        val listMyData: Type = Types.newParameterizedType(MutableList::class.java, PokemonItem::class.java)
        val jsonAdapter: JsonAdapter<List<PokemonItem>> = Moshi.Builder().build().adapter(listMyData)
        return jsonAdapter.toJson(images)
    }

    @TypeConverter
    fun fromStatDbData(json: String): List<Stat>? {
        val listMyData: Type = Types.newParameterizedType(MutableList::class.java, Stat::class.java)
        val adapter: JsonAdapter<List<Stat>> = Moshi.Builder().build().adapter(listMyData)
        return adapter.fromJson(json);
    }

    @TypeConverter
    fun fromStatDbData(images: List<Stat>): String {
        val listMyData: Type = Types.newParameterizedType(MutableList::class.java, Stat::class.java)
        val jsonAdapter: JsonAdapter<List<Stat>> = Moshi.Builder().build().adapter(listMyData)
        return jsonAdapter.toJson(images)
    }

    @TypeConverter
    fun fromTypeDbData(json: String): List<com.example.entity.localmodels.Type>? {
        val listMyData: Type = Types.newParameterizedType(MutableList::class.java, com.example.entity.localmodels.Type::class.java)
        val adapter: JsonAdapter<List<com.example.entity.localmodels.Type>> = Moshi.Builder().build().adapter(listMyData)
        return adapter.fromJson(json);
    }

    @TypeConverter
    fun fromTypeDbData(images: List<com.example.entity.localmodels.Type>): String {
        val listMyData: Type = Types.newParameterizedType(MutableList::class.java, com.example.entity.localmodels.Type::class.java)
        val jsonAdapter: JsonAdapter<List<com.example.entity.localmodels.Type>> = Moshi.Builder().build().adapter(listMyData)
        return jsonAdapter.toJson(images)
    }
}