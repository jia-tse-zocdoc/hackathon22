package com.example.hackathon22

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon22.databinding.SuccessLayoutBinding

class SuccessDialogFragment  : BaseDialogFragmentWithBinding<SuccessLayoutBinding>() {

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) = SuccessLayoutBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.image.playAnimation()
    }

    override fun onStart() {
        super.onStart()

        if (dialog == null) {
            return
        }

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        dialog?.window?.setWindowAnimations(R.style.dialog_animation_fade);
    }
    companion object {
        const val TAG = "SuccessDialogFragment"
    }
}