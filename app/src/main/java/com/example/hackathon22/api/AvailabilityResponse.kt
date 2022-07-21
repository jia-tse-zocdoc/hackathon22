package com.example.hackathon22.api

import com.squareup.moshi.Json

data class AvailabilityResponse(
    val data: List<AvailabilityResponseData>
)

data class AvailabilityResponseData(
    @Json(name = "first_availability") val firstAvailability: Availability,
    @Json(name = "provider_location_id") val id: String
)

data class Availability(
    @Json(name = "start_time") val startTime: String
)