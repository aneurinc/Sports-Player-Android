package com.aneurinc.sportsplayer.core.di

import android.content.Context
import com.aneurinc.sportsplayer.App
import dagger.Module
import dagger.Provides
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
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
