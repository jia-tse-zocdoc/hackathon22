package com.example.hackathon22.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hackathon22.BaseActivityWithBinding
import com.example.hackathon22.databinding.MainLayoutBinding
import com.example.hackathon22.triage.TriageAdapter
import com.example.hackathon22.triage.TriageModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivityWithBinding<MainLayoutBinding>(){

    private val viewModel: MainViewModel by viewModels()

    lateinit var triageAdapter: TriageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.triageState
                .onEach(::bindTriage)
                .launchIn(this)
        }

        triageAdapter = TriageAdapter()
        binding.triageRecycler.apply {
            adapter = triageAdapter
            isNestedScrollingEnabled = true
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }
    }

    private fun bindTriage(models: List<TriageModel>) {
        Log.e(TAG, "bindingTriage Models")
        triageAdapter.submitList(models)
    }

    override fun getViewBinding() = MainLayoutBinding.inflate(layoutInflater)

    companion object {
        const val TAG = "MainActivity"
    }
}