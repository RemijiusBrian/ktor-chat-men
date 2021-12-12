package com.chatmen.data.request

data class CreateAccountRequest(
    val username: String,
    val password: String,
    val name: String?,
    val email: String?,
)