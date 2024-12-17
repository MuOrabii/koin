package com.example.koin.data

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Observable<LoginResponse>

    @GET("profile")
    fun getProfile(): Observable<ProfileResponse>

    @GET("home")
    fun getHome(): Observable<HomeResponse>
}
