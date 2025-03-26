package com.example.user_service.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class KafkaMessageDto @JsonCreator constructor(
    @JsonProperty("email") val email: String,
    @JsonProperty("url") val url: String
)
