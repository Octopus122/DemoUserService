package com.example.user_service.model.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import org.apache.kafka.common.config.types.Password


data class UserLogin @JsonCreator constructor(
    @JsonProperty("login") val login: String,
    @JsonProperty("password") val password: String
) {
}