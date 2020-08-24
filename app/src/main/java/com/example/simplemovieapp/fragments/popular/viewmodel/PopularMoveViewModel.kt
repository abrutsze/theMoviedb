package com.example.simplemovieapp.fragments.popular.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactors.PopularMoveInteractor
import com.example.entities.Constants.Companion.ERROR_NULL_DATA
import com.example.entities.Constants.Companion.EXAPTION_FINISH_PAGINITION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.entities.Result
import com.example.entities.localmodels.PopularMoveItem

class PopularMoveViewModel(private val popularMoveInteractor: PopularMoveInteractor) : ViewModel() {

    private val _getPopularMoveList by lazy { MutableLiveData<List<PopularMoveItem>>() }
    val getPopularMoveList get() = _getPopularMoveList
    private val _loadingData by lazy { MutableLiveData<Boolean>() }
    val loadingData get() = _loadingData
    private val _errorNullData by lazy { MutableLiveData<String>() }
    val errorNullData get() = _errorNullData
    private val _finishPaginition by lazy { MutableLiveData<Boolean>() }
    val finishPaginition get() = _finishPaginition

    init {
        getPopularMoveList()
        _loadingData.value = true
    }

    fun getPopularMoveList() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val userData = popularMoveInteractor.getPopularMoveData()) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _getPopularMoveList.value = userData.data
                    _loadingData.value = false
                    _finishPaginition.value = false
                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    if (userData.errors.errorCode == ERROR_NULL_DATA) {
                        userData.errors.errorMessage?.apply {
                            _errorNullData.value = userData.errors.errorMessage
                            _loadingData.value = false
                        }
                    } else {
                        userData.errors.errorMessage?.apply {
                            _finishPaginition.value = true
                        }

                    }
                }
            }
        }
    }
}