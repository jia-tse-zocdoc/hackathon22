package com.example.hackathon22

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivityWithBinding<T : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    protected abstract fun getViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }
}
