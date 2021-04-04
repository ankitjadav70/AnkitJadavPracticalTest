package com.example.ankitjadavpracticaltest.ui.exchangerate

import android.app.DatePickerDialog
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ankitjadavpracticaltest.Utils.Consts
import com.example.ankitjadavpracticaltest.data.entity.CustomRate
import com.example.ankitjadavpracticaltest.data.entity.ExchangeRate
import com.example.ankitjadavpracticaltest.data.repository.ExchangeRateRepo
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ExchangeRateListViewModel(private val exchangeRateRepo: ExchangeRateRepo) : ViewModel() {
    var selectedDate = MutableLiveData<String>()
    private var errorMessage = MutableLiveData<String>()
    private var showProgress = MutableLiveData<Boolean>()

    init {
        val date = getCurrentDateTime()
        selectedDate.value = date.toString(Consts.DATE_FORMAT)
    }

    private lateinit var exchangeList: LiveData<ExchangeRate>

    fun getExchangeRateList(): LiveData<ExchangeRate> {
        exchangeList = exchangeRateRepo.getExchangeRates(selectedDate.value.toString())
        return exchangeList
    }

    fun observeErrorMessage(): LiveData<String> {
        errorMessage = exchangeRateRepo.getErrorMessage()
        return errorMessage
    }

    fun toShowProgress() : LiveData<Boolean> {
        showProgress=exchangeRateRepo.toShowProgress()
        return showProgress
    }



    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }


    fun convertMapToList(it: ExchangeRate): ArrayList<CustomRate> {
        val rateList = ArrayList<CustomRate>()
        for(key in it.rates.keys) {
            val rate: CustomRate = CustomRate()
            rate.currency=key
            rate.rate=it.rates[key]
            rateList.add(rate)
        }
        return rateList
    }

    fun onDateSelect(view: View) {
        val c = Calendar.getInstance()
        val cyear = c.get(Calendar.YEAR)
        val cmonth = c.get(Calendar.MONTH)
        val cday = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            view.context,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                selectedDate.value = c.time.toString(Consts.DATE_FORMAT)
                getExchangeRateList()

            }, cyear, cmonth, cday
        )

        dpd.show()
    }


}




