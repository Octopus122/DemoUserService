package com.example.user_service.model.response


data class UserResponse(
    val id: Long,
    val login: String,
    val email: String,
    val confirmation: Boolean,
    val bio: String
)
