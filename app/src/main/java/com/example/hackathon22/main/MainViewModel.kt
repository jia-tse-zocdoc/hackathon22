package com.example.hackathon22.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon22.R
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

    init {
        Log.e(TAG, "init view model")
        setupTriage()
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


    private fun emitTriage(list: List<TriageModel>) {
        viewModelScope.launch { _triageState.value = list }
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}