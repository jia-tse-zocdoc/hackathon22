package com.example.hackathon22.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.hackathon22.BaseActivityWithBinding
import com.example.hackathon22.databinding.ProfileLayoutBinding
import com.example.hackathon22.doctors.DoctorModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProfileActivity : BaseActivityWithBinding<ProfileLayoutBinding>(){

    private val viewModel: ProfileViewModel by viewModels {ProfileVMFactory(
        intent.getParcelableExtra("data")!!
    )}

    override fun getViewBinding() = ProfileLayoutBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.profileState
                .onEach(::bindProfile)
                .launchIn(this)
        }
    }

    private fun bindProfile(model: ProfileModel) {
        binding.bookButton.setOnClickListener { model.bookClicked() }
        binding.image.setImageResource(model.doctorModel.imageRes)
        binding.name.text = model.doctorModel.name
        binding.specialty.text = model.doctorModel.specialty
    }

    companion object {
        const val TAG = "ProfileActivity"

        fun getIntent(context: Context, doctorModel: DoctorModel): Intent {
            return Intent(context, ProfileActivity::class.java)
                .apply {
                    putExtra("data", doctorModel)
                }
        }
    }
}