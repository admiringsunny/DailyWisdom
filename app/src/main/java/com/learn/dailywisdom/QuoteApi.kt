package com.learn.dailywisdom

import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// API: https://dummyjson.com/quotes/random
interface QuoteApi {
    @GET("random")
    suspend fun getRandomQuote(): Quote
}


object RetrofitClient {
    val api: QuoteApi = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/quotes/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuoteApi::class.java)
}