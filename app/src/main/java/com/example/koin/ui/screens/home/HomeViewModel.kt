package com.example.koin.ui.screens.home

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.data.ApiService
import com.example.koin.data.HomeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val apiService: ApiService) : ViewModel() {
    val homeData = MutableLiveData<HomeResponse>()

    @SuppressLint("CheckResult")
    fun fetchHomeData() {
        apiService.getHome()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> homeData.value = response },
                { error -> homeData.value = HomeResponse(false, error.message ?: "Error", null) }
            )
    }
}
