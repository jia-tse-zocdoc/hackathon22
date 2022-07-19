package com.example.hackathon22.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.hackathon22.BaseActivityWithBinding
import com.example.hackathon22.databinding.MainLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivityWithBinding<MainLayoutBinding>(){

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainButton.setOnClickListener { viewModel.buttonClicked() }
    }
    override fun getViewBinding() = MainLayoutBinding.inflate(layoutInflater)
}