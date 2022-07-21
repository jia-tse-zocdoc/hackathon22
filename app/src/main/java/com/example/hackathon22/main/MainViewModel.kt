package com.example.hackathon22.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon22.Constants
import com.example.hackathon22.R
import com.example.hackathon22.api.ApiService
import com.example.hackathon22.doctors.DoctorModel
import com.example.hackathon22.triage.TriageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _triageState = MutableStateFlow(emptyList<TriageModel>())
    val triageState: StateFlow<List<TriageModel>> = _triageState

    private val _doctorState = MutableStateFlow(emptyList<DoctorModel>())
    val doctorState: StateFlow<List<DoctorModel>> = _doctorState

    private val _action = MutableSharedFlow<Action>()
    val action: SharedFlow<Action> = _action

    @Inject lateinit var apiService: ApiService


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
            DoctorModel(name = "Jane Smith", imageRes = R.mipmap.female1, specialty = "Cardiologist"),
            DoctorModel(name = "Jill Smith", imageRes = R.mipmap.female2, specialty = "Pulmonologist"),
            DoctorModel(name = "Jack Smith", imageRes = R.mipmap.male1, specialty = "Pediatrician"),
        ).let(::emitDoctors)
    }


    private fun emitDoctors(list: List<DoctorModel>) {
        viewModelScope.launch { _doctorState.value = list }
    }

    private fun emitTriage(list: List<TriageModel>) {
        viewModelScope.launch { _triageState.value = list }
    }

    fun triageClicked(triageModel: TriageModel) {
        viewModelScope.launch {
            val specialtyId = when (triageModel.name.lowercase()) {
                "general" -> Constants.SP_PCP
                "cardio" -> Constants.SP_CARDIO
                "dental" -> Constants.SP_DENTIST
                "vision" -> Constants.SP_VISION
                "ortho" -> Constants.SP_ORTHO
                "mental" -> Constants.SP_MENTAL
                else -> Constants.SP_PCP
            }
            val response = apiService.search(specialtyId = specialtyId)
            val pl = response.data.providerLocations.first()
            val providerRepoonse = apiService.getProvider(pl.provider.npi)
            val provider = providerRepoonse.data.first().providers.firstOrNull()
            val availability = apiService.getAvailability(
                plId = pl.id,
                visitReasonId = provider!!.defaultVR
            ).data.first().firstAvailability

            DoctorModel(
                name = provider.fullName,
                specialty = provider.specialties.first(),
                imageRes =
                        if (provider.gender.equals("female", ignoreCase = true)) R.mipmap.female1
                        else R.mipmap.male1,
                startTime = availability.startTime,
                visitReasonId = provider.defaultVR,
                plId = pl.id
            ).also(::openProfile)
        }
    }

    fun doctorClicked(doctorModel: DoctorModel) {
        openProfile(doctorModel)
    }

    private fun openProfile(doctorModel: DoctorModel) {
        viewModelScope.launch { _action.emit(Action.OpenProfile(doctorModel)) }
    }

    sealed class Action {
        data class OpenProfile(val doctorModel: DoctorModel) : Action()
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}