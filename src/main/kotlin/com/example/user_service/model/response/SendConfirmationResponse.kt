package com.example.user_service.model.response

data class SendConfirmationResponse (
    private val message: String,
    private val success: Boolean
)