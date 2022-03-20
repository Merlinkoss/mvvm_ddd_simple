package com.demo.anton_kondratiuk

import com.demo.anton_kondratiuk.models.RestCountriesFlags
import com.demo.anton_kondratiuk.models.RestCountriesModel
import com.demo.anton_kondratiuk.models.RestCountriesName
import com.demo.anton_kondratiuk.usecases.toCountryUIModel
import org.junit.Assert
import org.junit.Test

class ConverterTest {

    @Test
    fun `Returned full data from server`() {
        val countryModel = WEB_SERVER_MOCK_DATA
                .first()
                .toCountryUIModel()

        Assert.assertEquals(countryModel.name, "official")
        Assert.assertEquals(countryModel.capital, "capital")
        Assert.assertEquals(countryModel.flagUrl, "png")
    }

    @Test
    fun `Returned null data from server`() {
        val countryModel = WEB_SERVER_MOCK_DATA_NULL
                .first()
                .toCountryUIModel()

        Assert.assertEquals(countryModel.name, "")
        Assert.assertEquals(countryModel.capital, "")
        Assert.assertEquals(countryModel.flagUrl, null)
    }

    @Test
    fun `Returned part of null data from server 1`() {
        val countryModel = WEB_SERVER_MOCK_DATA_PART_NULLABLE_1
                .first()
                .toCountryUIModel()

        Assert.assertEquals(countryModel.name, "common")
        Assert.assertEquals(countryModel.capital, "capital")
        Assert.assertEquals(countryModel.flagUrl, "png")
    }

    @Test
    fun `Returned part of null data from server 2`() {
        val countryModel = WEB_SERVER_MOCK_DATA_PART_NULLABLE_2
                .first()
                .toCountryUIModel()

        Assert.assertEquals(countryModel.name, "official")
        Assert.assertEquals(countryModel.capital, "capital1")
        Assert.assertEquals(countryModel.flagUrl, "svg")
    }

    @Test
    fun `Returned part of null data from server 3`() {
        val countryModel = WEB_SERVER_MOCK_DATA_PART_NULLABLE_3
                .first()
                .toCountryUIModel()

        Assert.assertEquals(countryModel.name, "common")
        Assert.assertEquals(countryModel.capital, "")
        Assert.assertEquals(countryModel.flagUrl, null)
    }

    companion object {

        val WEB_SERVER_MOCK_DATA = listOf(
                RestCountriesModel(
                        RestCountriesName("common", "official"),
                        listOf("capital"),
                        RestCountriesFlags("png", "url")
                )
        )

        val WEB_SERVER_MOCK_DATA_NULL = listOf(
                RestCountriesModel(
                        RestCountriesName(null, null),
                        null,
                        RestCountriesFlags(null, null)
                )
        )

        val WEB_SERVER_MOCK_DATA_PART_NULLABLE_1 = listOf(
                RestCountriesModel(
                        RestCountriesName("common", null),
                        listOf("capital"),
                        RestCountriesFlags("png", null)
                )
        )

        val WEB_SERVER_MOCK_DATA_PART_NULLABLE_2 = listOf(
                RestCountriesModel(
                        RestCountriesName(null, "official"),
                        listOf("capital1", "capital2"),
                        RestCountriesFlags(null, "svg")
                )
        )

        val WEB_SERVER_MOCK_DATA_PART_NULLABLE_3 = listOf(
                RestCountriesModel(
                        RestCountriesName(null, "common"),
                        null,
                        RestCountriesFlags(null, null)
                )
        )

    }

}