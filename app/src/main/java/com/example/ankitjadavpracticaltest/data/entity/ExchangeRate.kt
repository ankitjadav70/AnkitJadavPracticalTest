package com.example.ankitjadavpracticaltest.data.entity

data class ExchangeRate(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: Rates?=null,
    val timestamp: Int
)