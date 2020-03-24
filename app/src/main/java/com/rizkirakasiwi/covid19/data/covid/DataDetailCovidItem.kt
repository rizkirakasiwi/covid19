package com.rizkirakasiwi.covid19.data.covid

data class DataDetailCovidItem(
    val active: Int,
    val admin2: Any,
    val combinedKey: String,
    val confirmed: Int,
    val countryRegion: String,
    val deaths: Int,
    val fips: Any,
    val incidentRate: Any,
    val iso2: String,
    val iso3: String,
    val lastUpdate: Long,
    val lat: Double,
    val long: Double,
    val peopleTested: Any,
    val provinceState: Any,
    val recovered: Int
)