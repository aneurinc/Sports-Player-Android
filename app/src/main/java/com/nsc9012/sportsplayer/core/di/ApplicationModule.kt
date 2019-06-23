package com.nsc9012.sportsplayer.core.di

import android.content.Context
import com.nsc9012.sportsplayer.App
import com.nsc9012.sportsplayer.BuildConfig
import com.nsc9012.sportsplayer.core.platform.LoggingInterceptor
import com.nsc9012.sportsplayer.features.athletes.AthletesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: App) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://206.189.172.18/api/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = LoggingInterceptor()
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideAthletesRepository(dataSource: AthletesRepository.Network): AthletesRepository = dataSource
}
