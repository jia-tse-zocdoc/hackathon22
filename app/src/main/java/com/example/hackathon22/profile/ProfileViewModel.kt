package com.example.hackathon22.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hackathon22.doctors.DoctorModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfileViewModel (
    doctorModel: DoctorModel
) : ViewModel() {


    private val _profileState = MutableStateFlow(ProfileModel())
    val profileState: StateFlow<ProfileModel> = _profileState

    init {
        emitState(ProfileModel(
            doctorModel = doctorModel,
            time = "time: 11:30am",
            bookClicked = {bookClicked()}
        ))
    }

    private fun bookClicked() {
        Log.e(TAG, "Book clicked")
    }

    private fun emitState(profileModel: ProfileModel) {
        Log.e(TAG, "emitting profileModel")
        viewModelScope.launch { _profileState.value = profileModel }
    }

    companion object {
        const val TAG = "ProfileViewModel"
    }
}

class ProfileVMFactory(private val doctorModel: DoctorModel): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ProfileViewModel(doctorModel) as T
}