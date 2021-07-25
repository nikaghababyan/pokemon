package com.example.pokemon.di

import com.example.pokemon.fragment.data.viewmodel.DataViewModel
import com.example.pokemon.fragment.pokemon.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DataViewModel(get()) }
    viewModel { PokemonViewModel(get()) }
}
