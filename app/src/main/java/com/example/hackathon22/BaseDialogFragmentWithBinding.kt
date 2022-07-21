package com.example.hackathon22

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragmentWithBinding <T : ViewBinding> : DialogFragment() {

    private var _binding: T? = null

    protected val binding get() = _binding!!

    protected abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return requireNotNull(binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
