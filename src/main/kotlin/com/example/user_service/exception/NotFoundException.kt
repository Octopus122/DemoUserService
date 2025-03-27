package com.example.user_service.exception

class NotFoundException(override val message: String?): Exception(message) {
}