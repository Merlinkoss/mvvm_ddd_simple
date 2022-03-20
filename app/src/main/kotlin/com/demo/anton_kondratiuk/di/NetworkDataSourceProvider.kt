package com.demo.anton_kondratiuk.di

import com.demo.anton_kondratiuk.data.NetworkDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class NetworkDataSourceProvider : Provider<NetworkDataSource> {

    override fun get(): NetworkDataSource = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkDataSource::class.java)

    companion object {

        private const val BASE_URL = "https://restcountries.com/v3.1/"

    }

}