package com.rizkirakasiwi.covid19.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizkirakasiwi.covid19.data.DataForHomeUi

class HomeViewModel : ViewModel() {
    private val _dataHome = MutableLiveData<DataForHomeUi>()
    val dataHome : LiveData<DataForHomeUi> get() = _dataHome

    fun setDataHome(data:DataForHomeUi){
        _dataHome.postValue(data)
    }
}
