package com.example.ankitjadavpracticaltest.data.entity

import com.google.gson.annotations.SerializedName

data class ExchangeRate(
    val base: String,
    val disclaimer: String,
    val license: String,
//    val rates: Rates?=null,
    val timestamp: Int,
    @SerializedName("rates")
   val rates: Map<String, String>
)