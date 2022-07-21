package com.example.hackathon22.hilt

import com.example.hackathon22.api.ApiService
import com.example.hackathon22.api.AuthInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
object AppModule {

    @Provides
    fun providesMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(authInterceptor)
        return builder.build()
    }

    @Provides
    fun provideApiService(
        moshiConverterFactory: MoshiConverterFactory,
        httpClient: OkHttpClient
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api-developer-sandbox.zocdoc.com/")
            .client(httpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(ApiService::class.java)
    }
}