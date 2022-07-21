package com.example.hackathon22.doctors

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DoctorModel(
    val name: String = "",
    val specialty: String = "",
    val imageRes: Int = 0,
    val startTime: String = "",
    val visitReasonId: String = "",
    val plId: String = "",
) : Parcelable