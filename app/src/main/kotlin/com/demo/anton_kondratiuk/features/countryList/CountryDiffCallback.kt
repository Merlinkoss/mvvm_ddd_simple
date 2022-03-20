package com.demo.anton_kondratiuk.features.countryList

import androidx.recyclerview.widget.DiffUtil
import com.demo.anton_kondratiuk.models.CountryUIModel

class CountryDiffCallback : DiffUtil.ItemCallback<CountryUIModel>() {

    override fun areItemsTheSame(oldItem: CountryUIModel, newItem: CountryUIModel) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: CountryUIModel, newItem: CountryUIModel) =
        oldItem == newItem
}