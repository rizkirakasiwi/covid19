package com.rizkirakasiwi.covid19.data.news

data class DataNews(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)