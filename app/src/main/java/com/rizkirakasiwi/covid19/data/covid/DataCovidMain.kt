package com.rizkirakasiwi.covid19.data.covid

data class DataCovidMain(
    val confirmed: DataCovidMainItem,
    val deaths: DataCovidMainItem,
    val lastUpdate: String,
    val recovered: DataCovidMainItem
)