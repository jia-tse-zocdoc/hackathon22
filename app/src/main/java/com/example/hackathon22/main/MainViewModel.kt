package com.example.hackathon22.main

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    fun buttonClicked() {
        Log.e("JIA", "button clicked")
    }
}