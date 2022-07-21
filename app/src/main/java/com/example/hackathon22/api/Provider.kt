package com.example.hackathon22.api

import com.squareup.moshi.Json

data class Provider(
    @Json(name = "full_name")
    val fullName: String = "",
    val npi: String = "",
    val specialties: List<String> = emptyList(),
    @Json(name = "gender_identity")
    val gender: String = "",
    @Json(name = "default_visit_reason_id")
    val defaultVR: String = ""
)