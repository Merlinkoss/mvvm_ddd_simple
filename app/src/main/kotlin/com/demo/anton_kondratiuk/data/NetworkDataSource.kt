package com.demo.anton_kondratiuk.data

import com.demo.anton_kondratiuk.models.RestCountriesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkDataSource {

    @GET("all/")
    suspend fun getAllCountries(): List<RestCountriesModel>

    @GET("name/{name}")
    suspend fun getCountriesByName(
            @Path("name") name : String
    ): List<RestCountriesModel>

}