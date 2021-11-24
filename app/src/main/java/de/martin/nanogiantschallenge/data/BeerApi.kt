package de.martin.nanogiantschallenge.data

import de.martin.nanogiantschallenge.data.model.Beer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BeerApi {
    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: String,
        @Query("per_page") query: String
    ): ArrayList<Beer>

    @GET("beers/{name}")
    suspend fun getBeer(@Path("beer_name") name: String): Beer

    @GET("beers/random")
    suspend fun getRandomBeer(): Beer
}
