package com.example.koin.ui.screens.profile

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.data.ApiService
import com.example.koin.data.ProfileResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(private val apiService: ApiService) : ViewModel() {
    val profileData = MutableLiveData<ProfileResponse>()

    @SuppressLint("CheckResult")
    fun fetchProfile() {
        apiService.getProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> profileData.value = response },
                { error -> profileData.value = ProfileResponse(false, error.message ?: "Error", null) }
            )
    }
}
