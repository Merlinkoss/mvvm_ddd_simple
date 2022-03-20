package com.demo.anton_kondratiuk.usecases

import com.demo.anton_kondratiuk.models.CountryUIModel
import com.demo.anton_kondratiuk.models.RestCountriesModel

fun RestCountriesModel.toCountryUIModel() =
    CountryUIModel(
            name = name?.official ?: name?.common.orEmpty(),
            capital = capital?.firstOrNull().orEmpty(),
            flagUrl = flags?.png ?: flags?.svg
    )