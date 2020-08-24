package com.example.simplemovieapp.fragments.moviedetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entities.Constants.Companion.ERROR_NULL_DATA
import com.example.domain.interactors.DetailMoveInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.entities.Result
import com.example.entities.localmodels.DetailMove
import com.example.entities.localmodels.TopCast

class DetailMoveViewModel(private val detailMoveInteractor: DetailMoveInteractor) : ViewModel() {

    private val _getDetailMoveData by lazy { MutableLiveData<DetailMove>() }
    val getDetailMoveData get() = _getDetailMoveData
    private val _getTopCastMoveData by lazy { MutableLiveData<TopCast>() }
    val getTopCastMoveData get() = _getTopCastMoveData
    private val _loadingData by lazy { MutableLiveData<Boolean>() }
    val loadingData get() = _loadingData
    private val _errorNullData by lazy { MutableLiveData<String>() }
    val errorNullData get() = _errorNullData


    fun getDetailData(detailMoveId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val userData = detailMoveInteractor.getDetailMoveData(detailMoveId)) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _getDetailMoveData.value = userData.data
                    _loadingData.value=false
                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    if (userData.errors.errorCode == ERROR_NULL_DATA) {
                        userData.errors.errorMessage?.apply {
                            _errorNullData.value = userData.errors.errorMessage
                            _loadingData.value=false
                        }
                    }
                }
            }
        }
    }
    fun getTopCastData(detailMoveId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val userData = detailMoveInteractor.getTopCastMoveData(detailMoveId)) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _getTopCastMoveData.value = userData.data
                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    if (userData.errors.errorCode == ERROR_NULL_DATA) {
                        userData.errors.errorMessage?.apply {
                            _errorNullData.value = userData.errors.errorMessage
                        }
                    }
                }
            }
        }
    }
}

