package com.demo.anton_kondratiuk.data

import toothpick.InjectConstructor

@InjectConstructor
class CountryRepository(
        private var networkDataSource: NetworkDataSource
) {

    suspend fun getAllCountries() = networkDataSource.getAllCountries()

    suspend fun getCountriesByName(name: String) = networkDataSource.getCountriesByName(name)

}