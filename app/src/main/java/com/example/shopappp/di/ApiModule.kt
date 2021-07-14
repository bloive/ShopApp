package com.example.shopappp.di

import com.example.shopappp.BuildConfig
import com.example.shopappp.network.AuthService
import com.example.shopappp.network.PostService
import com.example.shopappp.repository.auth.AuthRepository
import com.example.shopappp.repository.auth.AuthRepositoryImpl
import com.example.shopappp.repository.post.PostRepository
import com.example.shopappp.repository.post.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    companion object {
        private const val BASE_URL = "https://ktorhighsteaks.herokuapp.com/"
    }

    private fun httpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                chain.request()
                val request = chain.request().newBuilder()
                return chain.proceed(request.build())
            }
        })
        if (BuildConfig.BUILD_TYPE == "debug") {
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            )
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun loginService(): AuthService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient())
            .build()
            .create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideLoginRepo(authService: AuthService): AuthRepository = AuthRepositoryImpl(authService)

    @Provides
    @Singleton
    fun providePostRepo(postService: PostService): PostRepository =
        PostRepositoryImpl(postService)

}