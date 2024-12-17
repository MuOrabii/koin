package com.example.koin.data

// Login API Models

data class LoginRequest(val email: String, val password: String)

data class LoginResponse(
    val status: Boolean,
    val message: String,
    val data: LoginData?
)

data class LoginData(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val image: String,
    val points: Int,
    val credit: Int,
    val token: String
)

// Profile API Models

data class ProfileResponse(
    val status: Boolean,
    val message: String?,
    val data: ProfileData?
)

data class ProfileData(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val image: String,
    val points: Int,
    val credit: Int,
    val token: String
)

// Home API Models

data class HomeResponse(
    val status: Boolean,
    val message: String?,
    val data: HomeData?
)

data class HomeData(
    val banners: List<Banner>,
    val products: List<Product>,
    val ad: String
)

data class Banner(
    val id: Int,
    val image: String
)

data class Product(
    val id: Int,
    val price: Double,
    val old_price: Double,
    val discount: Int,
    val image: String,
    val name: String,
    val description: String,
    val in_favorites: Boolean,
    val in_cart: Boolean
)
