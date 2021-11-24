package de.martin.nanogiantschallenge

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.martin.nanogiantschallenge.data.BeerApi
import de.martin.nanogiantschallenge.repository.BeerRepository
import de.martin.nanogiantschallenge.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideBeerApi(): BeerApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(BeerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBeerRepository(
        api: BeerApi
    ) = BeerRepository(api)
}