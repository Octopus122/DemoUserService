package com.example.user_service.exception

class EmailAlreadyConfirmedException(override val message: String?) : Exception(message) {
}