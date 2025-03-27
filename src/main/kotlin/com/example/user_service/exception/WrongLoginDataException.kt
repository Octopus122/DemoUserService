package com.example.user_service.exception

class WrongLoginDataException(override val message: String?) : Exception(message) {
}