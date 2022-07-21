package com.example.hackathon22.api

import com.squareup.moshi.Json

data class ProviderLocation(
    val provider : Provider,
    @Json(name = "provider_location_id")
    val id: String
)