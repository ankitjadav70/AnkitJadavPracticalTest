package com.example.ankitjadavpracticaltest.ui.exchangerate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ankitjadavpracticaltest.R
import com.example.ankitjadavpracticaltest.data.entity.CustomRate
import com.example.ankitjadavpracticaltest.databinding.ItemRateBinding

class ExchangeRateAdapter(private val rateList : ArrayList<CustomRate>) : RecyclerView.Adapter<ExchangeRateAdapter.RateViewHolder>() {

    inner class RateViewHolder(val binding: ItemRateBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val binding : ItemRateBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_rate,parent,false)
        return RateViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rateList.size
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val customRate=rateList[position]
        holder.binding.customRate=customRate
    }
}