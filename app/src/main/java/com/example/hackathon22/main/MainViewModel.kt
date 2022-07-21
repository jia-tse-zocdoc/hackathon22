package com.example.hackathon22.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon22.R
import com.example.hackathon22.doctors.DoctorModel
import com.example.hackathon22.triage.TriageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {


    private val _triageState = MutableStateFlow(emptyList<TriageModel>())
    val triageState: StateFlow<List<TriageModel>> = _triageState

    private val _doctorState = MutableStateFlow(emptyList<DoctorModel>())
    val doctorState: StateFlow<List<DoctorModel>> = _doctorState

    init {
        Log.e(TAG, "init view model")
        setupTriage()
        setupDoctors()
    }

    private fun setupTriage() {
        listOf(
            TriageModel(name = "General", imageRes = R.mipmap.general),
            TriageModel(name = "Cardio", imageRes = R.mipmap.cardio),
            TriageModel(name = "Dental", imageRes = R.mipmap.dental),
            TriageModel(name = "Vision", imageRes = R.mipmap.glasses),
            TriageModel(name = "Ortho", imageRes = R.mipmap.ortho),
            TriageModel(name = "Mental", imageRes = R.mipmap.mental),
        ).let(::emitTriage)
    }

    private fun setupDoctors() {
        listOf(
            DoctorModel(name = "Jane Smith", imageRes = R.mipmap.doc1, specialty = "Cardiologist"),
            DoctorModel(name = "Jill Smith", imageRes = R.mipmap.doc2, specialty = "Pulmonologist"),
            DoctorModel(name = "Jack Smith", imageRes = R.mipmap.doc3, specialty = "Pediatrician"),
        ).let(::emitDoctors)
    }


    private fun emitDoctors(list: List<DoctorModel>) {
        viewModelScope.launch { _doctorState.value = list }
    }

    private fun emitTriage(list: List<TriageModel>) {
        viewModelScope.launch { _triageState.value = list }
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}