package com.example.hackathon22.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hackathon22.doctors.DoctorModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfileViewModel(
    doctorModel: DoctorModel
) : ViewModel() {

    private val _action = MutableSharedFlow<Action>()
    val action: SharedFlow<Action> = _action

    private val _profileState = MutableStateFlow(ProfileModel())
    val profileState: StateFlow<ProfileModel> = _profileState

    init {
        emitState(ProfileModel(
            doctorModel = doctorModel,
            time = "time: 11:30am",
            bookClicked = { bookClicked(doctorModel) }
        ))
    }

    private fun bookClicked(doctorModel: DoctorModel) {
        emitAction(Action.ShowSuccess(doctorModel))
    }

    private fun emitState(profileModel: ProfileModel) {
        Log.e(TAG, "emitting profileModel")
        viewModelScope.launch { _profileState.value = profileModel }
    }

    private fun emitAction(action: Action) {
        viewModelScope.launch { _action.emit(action) }
    }

    companion object {
        const val TAG = "ProfileViewModel"
    }

    sealed class Action {
        data class ShowSuccess(val doctorModel: DoctorModel) : Action()
    }

}

class ProfileVMFactory(private val doctorModel: DoctorModel) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ProfileViewModel(doctorModel) as T
}