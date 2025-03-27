package com.example.user_service.exception

import com.example.user_service.model.response.UserResponse

class CantSendConfirmMessageException(override val message: String, val registerData: UserResponse): Exception(message) {
}