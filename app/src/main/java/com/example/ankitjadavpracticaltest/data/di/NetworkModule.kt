package com.example.ankitjadavpracticaltest.data.di

import com.example.ankitjadavpracticaltest.BuildConfig
import com.example.ankitjadavpracticaltest.data.network.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://openexchangerates.org/api/"

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()



private fun provideRetrofit(okHttpClient: OkHttpClient,BASE_URL: String,gson: Gson): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()


private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

private fun provideGson() =GsonBuilder()
    .setLenient()
    .create()



val networkModule= module {

    single { provideRetrofit(get(),BASE_URL,get()) }
    single { provideApiService(get())}
    single { provideOkHttpClient()}
    single { provideGson()}
}