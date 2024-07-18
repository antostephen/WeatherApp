package com.anto.posts.di

import android.content.SharedPreferences
import com.anto.core.utils.Constants.BASE_URL
import com.anto.posts.data.remote.ApiService
import com.anto.posts.domain.mappers.WeatherMapper
import com.anto.posts.domain.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    fun provideWeatherApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideWeatherRepository(
        apiService: ApiService, sharedPreferences: SharedPreferences,
        weatherMapper: WeatherMapper
    ): WeatherRepositoryImpl {
        return WeatherRepositoryImpl(apiService, sharedPreferences, weatherMapper)
    }
}
