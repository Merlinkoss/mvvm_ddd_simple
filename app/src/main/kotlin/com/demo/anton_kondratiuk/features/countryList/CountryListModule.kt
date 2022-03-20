package com.demo.anton_kondratiuk.features.countryList

import com.demo.anton_kondratiuk.usecases.GetAllCountryUseCase
import com.demo.anton_kondratiuk.usecases.GetCountriesByNameUseCase
import toothpick.config.Module
import toothpick.ktp.binding.bind

class CountryListModule : Module() {

    init {
        bind<GetAllCountryUseCase>().singleton().releasable()
        bind<GetCountriesByNameUseCase>().singleton().releasable()
    }

}