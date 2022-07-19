package com.example.hackathon22.hilt

import com.example.hackathon22.api.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideApiService(
        moshiConverterFactory: MoshiConverterFactory,
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(ApiService::class.java)
    }
}