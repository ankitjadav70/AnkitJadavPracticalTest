package com.example.ankitjadavpracticaltest.data.entity

data class ErrorBody(
    val description: String,
    val error: Boolean,
    val message: String,
    val status: Int
)