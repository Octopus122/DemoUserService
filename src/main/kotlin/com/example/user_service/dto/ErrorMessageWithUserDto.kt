package com.example.user_service.dto

import com.example.user_service.model.response.UserResponse

data class ErrorMessageWithUserDto (
    private val message: String,
    private val userData: UserResponse
)