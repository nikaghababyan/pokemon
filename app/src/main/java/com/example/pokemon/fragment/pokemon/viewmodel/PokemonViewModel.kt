package com.example.pokemon.fragment.pokemon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domian.interactors.PokemonInteractor
import com.example.entity.Constants.Companion.ERROR_NULL_DATA
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.entity.Result
import com.example.entity.localmodels.PokemonInfo


class PokemonViewModel(private val pokemonInteractor: PokemonInteractor) : ViewModel() {

    private val _getPokemonData by lazy { MutableLiveData<PokemonInfo>() }
    val getPokemonData get() = _getPokemonData
    private val _loadingData by lazy { MutableLiveData<Unit>() }
    val loadingData get() = _loadingData
    private val _errorNullData by lazy { MutableLiveData<String>() }
    val errorNullData get() = _errorNullData
    private val _deletePostItem by lazy { MutableLiveData<Int>() }
    val deletePostItem get() = _deletePostItem

    fun getPostData(pokemonUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val userData = pokemonInteractor.getPokemonResponse(pokemonUrl)) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _getPokemonData.value = userData.data
                    _loadingData.value=Unit
                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    if (userData.errors.errorCode == ERROR_NULL_DATA) {
                        userData.errors.errorMessage?.apply {
                            _errorNullData.value = userData.errors.errorMessage
                            _loadingData.value=Unit
                        }
                    }
                }
            }
        }
    }
}

