package com.example.user_service.model.request

data class UserUpdateRequest (
    val login: String,
    val bio: String
)