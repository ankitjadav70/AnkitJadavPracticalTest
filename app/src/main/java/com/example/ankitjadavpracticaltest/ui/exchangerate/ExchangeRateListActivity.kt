package com.example.ankitjadavpracticaltest.ui.exchangerate

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ankitjadavpracticaltest.R
import com.example.ankitjadavpracticaltest.data.entity.CustomRate
import com.example.ankitjadavpracticaltest.databinding.ActivityExchangeratelistBinding
import kotlinx.android.synthetic.main.activity_exchangeratelist.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel


class ExchangeRateListActivity : AppCompatActivity() {

    private val rateListViewModel : ExchangeRateListViewModel by viewModel()
    lateinit var binding: ActivityExchangeratelistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_exchangeratelist)
        binding.rate=rateListViewModel
        binding.lifecycleOwner = this

        rateListViewModel.getExchangeRateList().observe(this, Observer {
            CoroutineScope(Dispatchers.IO).launch {
                val rateList=rateListViewModel.convertMapToList(it)
                withContext(Dispatchers.Main) {
                    setExchangeRateAdapter(rateList)
                }
            }
        })

        rateListViewModel.observeErrorMessage().observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })

        rateListViewModel.toShowProgress().observe(this, Observer {
            if(it) {
                progressBar.visibility=View.VISIBLE
            }
            else{
                progressBar.visibility= View.GONE
            }
        })
    }

    fun setExchangeRateAdapter(rateList : ArrayList<CustomRate>) {
        with(rcy_ratelist) {
            this.layoutManager=GridLayoutManager(this@ExchangeRateListActivity,3)
            this.setHasFixedSize(true);
            this.addItemDecoration(DividerItemDecoration(this@ExchangeRateListActivity,
                DividerItemDecoration.HORIZONTAL))
            this.addItemDecoration( DividerItemDecoration(this@ExchangeRateListActivity,
                DividerItemDecoration.VERTICAL))
            this.adapter=ExchangeRateAdapter(rateList)
        }
    }

}