package com.example.user_service.model.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class UserRequestRegister @JsonCreator constructor(
    @JsonProperty("login") val login: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("bio") val bio: String
)
