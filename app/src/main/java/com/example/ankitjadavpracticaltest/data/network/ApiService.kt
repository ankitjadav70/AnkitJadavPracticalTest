package com.example.ankitjadavpracticaltest.data.network

import com.example.ankitjadavpracticaltest.data.entity.ExchangeRate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("historical/{date}.json?app_id=b8170f2960a546378a5ceb06a7bb6f59")
     fun getExchangeRates(@Path("date")date :String): Call<ExchangeRate>

}