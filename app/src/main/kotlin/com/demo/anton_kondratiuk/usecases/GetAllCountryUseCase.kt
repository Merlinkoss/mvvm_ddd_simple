package com.demo.anton_kondratiuk.usecases

import com.demo.anton_kondratiuk.data.CountryRepository
import com.demo.anton_kondratiuk.models.CountryUIModel
import com.demo.anton_kondratiuk.models.RestCountriesModel
import toothpick.InjectConstructor

@InjectConstructor
class GetAllCountryUseCase(
        private val countryRepository: CountryRepository
) {

    suspend operator fun invoke() : List<CountryUIModel> =
        countryRepository
                .getAllCountries()
                .map(RestCountriesModel::toCountryUIModel)

}