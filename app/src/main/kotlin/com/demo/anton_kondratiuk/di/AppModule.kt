package com.demo.anton_kondratiuk.di

import android.app.Application
import android.content.Context
import com.demo.anton_kondratiuk.data.CountryRepository
import com.demo.anton_kondratiuk.data.NetworkDataSource
import com.demo.anton_kondratiuk.di.utils.ViewModelFactory
import com.demo.anton_kondratiuk.usecases.GetAllCountryUseCase
import toothpick.config.Module
import toothpick.ktp.binding.bind

class AppModule(application: Application) : Module() {

    init {
        bind<Application>().toInstance(application)
        bind<Context>().toInstance(application.applicationContext)
        bind<ViewModelFactory>().singleton().releasable()
    }

}