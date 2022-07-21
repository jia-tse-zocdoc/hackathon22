package com.example.hackathon22.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.hackathon22.BaseActivityWithBinding
import com.example.hackathon22.SuccessDialogFragment
import com.example.hackathon22.databinding.ProfileLayoutBinding
import com.example.hackathon22.doctors.DoctorModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProfileActivity : BaseActivityWithBinding<ProfileLayoutBinding>() {

    private val viewModel: ProfileViewModel by viewModels {
        ProfileVMFactory(
            intent.getParcelableExtra("data")!!
        )
    }

    private val successDialog = SuccessDialogFragment()

    override fun getViewBinding() = ProfileLayoutBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.profileState
                .onEach(::bindProfile)
                .launchIn(this)

            viewModel.action
                .onEach { showSuccess(it as ProfileViewModel.Action.ShowSuccess) }
                .launchIn(this)
        }
    }

    private fun bindProfile(model: ProfileModel) {
        binding.bookButton.setOnClickListener { model.bookClicked() }
        binding.image.setImageResource(model.doctorModel.imageRes)
        binding.name.text = model.doctorModel.name
        binding.specialty.text = model.doctorModel.specialty
    }

    private fun showSuccess(action: ProfileViewModel.Action.ShowSuccess) {
        successDialog.show(supportFragmentManager, SuccessDialogFragment.TAG)
        setResult(
            RESULT_OK,
            Intent().apply {
                putExtra("result", action.doctorModel)
            }
        )

        val r = Runnable {
            successDialog.dismiss()
            finish()
        }
        Handler().postDelayed(r, 1500)
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