package com.example.hackathon22.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/provider_locations")
    suspend fun search(
        @Query("zip_code") zip: String = "11204",
        @Query("specialty_id") specialtyId: String,
        @Query("page") page: Int = 0,
        @Query("page_size") pageSize: Int = 10,
        @Query("visit_type") visitType: String = "all",
    ): SearchResponse

    @GET("v1/providers")
    suspend fun getProvider(
        @Query("npis") plId: String,
    ) : ProviderResponse

    @GET("v1/provider_locations/availability")
    suspend fun getAvailability(
        @Query("provider_location_ids") plId: String,
        @Query("visit_reason_id") visitReasonId: String,
        @Query("patient_type") patientType: String = "new",
    ): AvailabilityResponse


}