package com.example.hackathon22.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon22.BaseActivityWithBinding
import com.example.hackathon22.HorizontalMarginItemDecoration
import com.example.hackathon22.R
import com.example.hackathon22.databinding.MainLayoutBinding
import com.example.hackathon22.doctors.DoctorAdapter
import com.example.hackathon22.doctors.DoctorModel
import com.example.hackathon22.main.MainViewModel.Action.OpenProfile
import com.example.hackathon22.profile.ProfileActivity
import com.example.hackathon22.triage.TriageAdapter
import com.example.hackathon22.triage.TriageModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivityWithBinding<MainLayoutBinding>(){

    private val viewModel: MainViewModel by viewModels()

    lateinit var triageAdapter: TriageAdapter
    lateinit var doctorAdapter: DoctorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.triageState
                .onEach(::bindTriage)
                .launchIn(this)

            viewModel.doctorState
                .onEach(::bindDoctors)
                .launchIn(this)

            viewModel.action
                .onEach(::handleAction)
                .launchIn(this)
        }

        triageAdapter = TriageAdapter(viewModel::triageClicked)
        binding.triageRecycler.apply {
            adapter = triageAdapter
            isNestedScrollingEnabled = true
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }

        doctorAdapter = DoctorAdapter(viewModel::doctorClicked)

        binding.doctorsRecycler.apply {
            adapter = doctorAdapter
            isNestedScrollingEnabled = true
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            addItemDecoration(HorizontalMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.half_half_padding)))
        }
    }

    private fun handleAction(action: MainViewModel.Action) {
        startActivity(ProfileActivity.getIntent(this, (action as OpenProfile).doctorModel))
    }

    private fun bindTriage(models: List<TriageModel>) {
        Log.e(TAG, "bindingTriage Models")
        triageAdapter.submitList(models)
    }

    private fun bindDoctors(models: List<DoctorModel>) {
        Log.e(TAG, "bindDoctors Models")
        doctorAdapter.submitList(models)
    }

    override fun getViewBinding() = MainLayoutBinding.inflate(layoutInflater)

    companion object {
        const val TAG = "MainActivity"
    }
}