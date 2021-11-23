package de.martin.nanogiantschallenge.api

import de.martin.nanogiantschallenge.api.model.Beer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BeerApi {
    @GET("beers")
    suspend fun getBeers(@Query("per_page") query: String): ArrayList<Beer>

    @GET("beers/{id}")
    suspend fun getBeer(@Path("id") id: Int): Beer

}