package com.example.hackathon22.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/1/constants")
    suspend fun getProvider(
        @Query("include_all_specialties") includeSpecialties: Boolean,
        @Query("include_all_procedures") includeProcedures: Boolean,
    )

}