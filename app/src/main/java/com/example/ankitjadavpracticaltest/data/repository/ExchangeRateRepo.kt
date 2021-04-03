package com.example.ankitjadavpracticaltest.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ankitjadavpracticaltest.data.entity.ErrorBody
import com.example.ankitjadavpracticaltest.data.entity.ExchangeRate
import com.example.ankitjadavpracticaltest.data.network.ApiService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ExchangeRateRepo(private val apiService: ApiService) {

    private var userList = MutableLiveData<ExchangeRate>()
    private var errorMessage = MutableLiveData<String>()
    private var showProgress = MutableLiveData<Boolean>()

    fun getExchangeRates(selectedDate: String): MutableLiveData<ExchangeRate> {
        val call = apiService.getExchangeRates(selectedDate)
        showProgress.value=true
        call.enqueue(object : Callback<ExchangeRate> {
            override fun onFailure(call: Call<ExchangeRate>, t: Throwable) {
                Log.v("Response","onFailure")
                showProgress.value=false
            }

            override fun onResponse(call: Call<ExchangeRate>, response: Response<ExchangeRate>) {
                if(response.isSuccessful) {
                    userList.value = response.body()
                }
                else{
                    try {
                        val loginError= Gson().fromJson(response.errorBody()?.string(), ErrorBody::class.java)
                        errorMessage.value=loginError.description
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                showProgress.value=false

            }
        })
        return userList
    }

    fun getErrorMessage() : MutableLiveData<String> {
        return errorMessage
    }

    fun toShowProgress() : MutableLiveData<Boolean> {
        return showProgress
    }

}