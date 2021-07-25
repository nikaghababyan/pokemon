package com.example.domian.di

import com.example.domian.interactors.GetDataInteractor
import com.example.domian.interactors.PokemonInteractor
import com.example.domian.usecase.GetDataUseCase
import com.example.domian.usecase.PokemonUseCase
import org.koin.dsl.module

val interactorModule = module {
    factory<GetDataInteractor> { GetDataUseCase(get(),get(),get()) }
    single<PokemonInteractor> { PokemonUseCase(get()) }
}
