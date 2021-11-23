package de.martin.nanogiantschallenge.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: BeerApi by lazy {
        retrofit.create(BeerApi::class.java)
    }
}