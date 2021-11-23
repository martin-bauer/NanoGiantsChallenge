package de.martin.nanogiantschallenge.repository

import de.martin.nanogiantschallenge.api.model.Beer
import de.martin.nanogiantschallenge.api.RetrofitInstance

class BeerRepository {
    suspend fun getBeers(): ArrayList<Beer> {
        return RetrofitInstance.api.getBeers("50")
    }

    suspend fun getBeer(id: Int): Beer {
        return RetrofitInstance.api.getBeer(id)
    }
}