package com.example.hackathon22.profile

import com.example.hackathon22.doctors.DoctorModel

data class ProfileModel(
    val doctorModel: DoctorModel = DoctorModel(),
    val time: String = "",
    val bookClicked: () -> Unit = {}
)