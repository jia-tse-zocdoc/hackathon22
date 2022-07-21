package com.example.hackathon22.api

data class ProviderResponse(
    val data: List<ProviderResponseData>
)

data class ProviderResponseData(
    val npi: String,
    val providers: List<Provider>
)

