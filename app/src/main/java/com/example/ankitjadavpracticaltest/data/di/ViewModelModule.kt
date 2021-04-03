package com.example.ankitjadavpracticaltest.data.di

import com.example.ankitjadavpracticaltest.ui.exchangerate.ExchangeRateListViewModel
import com.example.ankitjadavpracticaltest.ui.login.LoginViewModel
import org.koin.dsl.module


val viewModelModule = module {
    single { LoginViewModel() }
    single { ExchangeRateListViewModel(get()) }
}