package com.example.user_service.service

import com.example.user_service.dto.KafkaMessageDto

interface KafkaService {
    fun sendMessage(message: KafkaMessageDto)
}