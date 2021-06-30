package com.example.shopappp.di

import com.example.shopappp.network.ApiService
import com.example.shopappp.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    companion object {
        private const val BASE_URL = "https://"
    }

    @Provides
    @Singleton
    fun loginService() =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)

    //TODO
}