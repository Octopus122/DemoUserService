package com.example.user_service.model.response

data class UserRegistrationResponse(
    val id: Long,
    val login: String,
    val email: String,
    val confirmation: Boolean,
    val bio: String,
    val sendConfirmation: Boolean
)