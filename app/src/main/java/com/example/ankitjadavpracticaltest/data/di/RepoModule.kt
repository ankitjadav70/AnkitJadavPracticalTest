package com.example.ankitjadavpracticaltest.data.di

import com.example.ankitjadavpracticaltest.data.repository.ExchangeRateRepo
import org.koin.dsl.module

val repoModule= module {
    single { ExchangeRateRepo(get()) }
}