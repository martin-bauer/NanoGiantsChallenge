package de.martin.nanogiantschallenge.repository

import dagger.hilt.android.scopes.ActivityScoped
import de.martin.nanogiantschallenge.data.BeerApi
import de.martin.nanogiantschallenge.data.model.Beer
import javax.inject.Inject

@ActivityScoped
class BeerRepository @Inject constructor(
    private val api: BeerApi
) {
    suspend fun getBeers(page: String, per_page: String): ArrayList<Beer> {
        return api.getBeers(page, per_page)
    }

    suspend fun getBeer(name: String): Beer {
        return api.getBeer(name)
    }

    suspend fun getRandomBeer(): Beer {
        return api.getRandomBeer()
    }
}
