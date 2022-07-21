package com.example.hackathon22.api

import com.squareup.moshi.Json

data class SearchResponse (
    val page: Int,
    @Json(name = "page_size")
    val pageSize: Int,
    val data: SearchResponseData
)

data class SearchResponseData(
    @Json(name ="provider_locations")
    val providerLocations: List<ProviderLocation>
)

