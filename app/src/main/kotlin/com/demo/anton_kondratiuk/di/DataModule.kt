package com.demo.anton_kondratiuk.di

import android.app.Application
import android.content.Context
import com.demo.anton_kondratiuk.data.CountryRepository
import com.demo.anton_kondratiuk.data.NetworkDataSource
import com.demo.anton_kondratiuk.usecases.GetAllCountryUseCase
import toothpick.config.Module
import toothpick.ktp.binding.bind

class DataModule : Module() {

    init {
        bind<NetworkDataSource>()
                .toProvider(NetworkDataSourceProvider::class)
                .providesSingleton()
                .providesReleasable()
        bind<CountryRepository>().singleton().releasable()
    }
}