package com.demo.anton_kondratiuk.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryUIModel(
        val name: String,
        val capital: String,
        val flagUrl: String?
) : Parcelable