package com.demo.anton_kondratiuk.models

data class RestCountriesModel(
        val name: RestCountriesName?,
        val capital: List<String>?,
        val flags: RestCountriesFlags?
)

data class RestCountriesName(
        val common : String?,
        val official: String?
)

data class RestCountriesFlags(
        val png: String?,
        val svg: String?
)