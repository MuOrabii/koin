package com.example.koin.ui.screens.login

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.data.ApiService
import com.example.koin.data.LoginRequest
import com.example.koin.data.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val apiService: ApiService) : ViewModel() {
    val loginState = MutableLiveData<LoginResponse>()

    @SuppressLint("CheckResult")
    fun login(email: String, password: String) {
        apiService.login(LoginRequest(email, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> loginState.value = response },
                { error -> loginState.value = LoginResponse(false, error.message ?: "Error", null) }
            )
    }
}
