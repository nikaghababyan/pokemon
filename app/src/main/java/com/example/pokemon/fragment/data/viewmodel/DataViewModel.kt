package com.example.pokemon.fragment.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domian.interactors.GetDataInteractor
import com.example.entity.Constants.Companion.ERROR_NULL_DATA
import com.example.entity.Result
import com.example.entity.localmodels.PokemonItem
import kotlinx.coroutines.*

class DataViewModel(
    private val getDataInteractor: GetDataInteractor
) : ViewModel() {

    private val _getUserDataList by lazy { MutableLiveData<List<PokemonItem>>() }
    val getUserDataList: LiveData<List<PokemonItem>> = _getUserDataList
    private val _getSortedList by lazy { MutableLiveData<List<PokemonItem>>() }
    val getSortedList: LiveData<List<PokemonItem>> = _getSortedList
    private val _loadingData by lazy { MutableLiveData<Boolean>() }
    val loadingData get() = _loadingData
    private val _errorNullData by lazy { MutableLiveData<String>() }
    val errorNullData get() = _errorNullData
    private val _finishPagination by lazy { MutableLiveData<Boolean>() }
    val finishPagination get() = _finishPagination


    fun getDataList() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val pokemonData = getDataInteractor.getDataResponse()) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _getUserDataList.value = pokemonData.data
                    _finishPagination.value = false
                    _loadingData.value = false
                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    if (pokemonData.errors.errorCode == ERROR_NULL_DATA) {
                        pokemonData.errors.errorMessage?.apply {
                            _errorNullData.value = pokemonData.errors.errorMessage
                            _finishPagination.value = false
                            _loadingData.value = false
                        }
                    } else {
                        pokemonData.errors.errorMessage?.apply {
                            _finishPagination.value = true
                        }

                    }
                }
            }
        }
    }

    fun sortBy(checkedAttack: Boolean, checkedDefence: Boolean, checkedHp: Boolean) {
        _getSortedList.value =
            getDataInteractor.sortListBy(checkedAttack, checkedDefence, checkedHp)
    }

    fun loadLocalData() {
        _getUserDataList.value = getDataInteractor.loadLocalDataDb()
        _loadingData.value = false
    }

}