package com.example.hackathon22.appointment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon22.R
import com.example.hackathon22.Utils
import com.example.hackathon22.databinding.AppointmentItemBinding
import com.example.hackathon22.doctors.DoctorModel

class AppointmentsAdapter : ListAdapter<DoctorModel, AppointmentViewHolder>(DiffCallback()) {

    private val colors = listOf(R.color.light_green, R.color.light_blue, R.color.light_pink)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder =
        AppointmentViewHolder(AppointmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class AppointmentViewHolder(private val binding: AppointmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DoctorModel) {
        binding.image.setImageResource(item.imageRes)
        binding.name.text = item.name
        binding.specialty.text = item.specialty
        binding.root.setCardBackgroundColor(Utils.generateRandomColor())
    }
}

class DiffCallback : DiffUtil.ItemCallback<DoctorModel>() {
    override fun areItemsTheSame(oldItem: DoctorModel, newItem: DoctorModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: DoctorModel, newItem: DoctorModel): Boolean {
        return oldItem == newItem
    }

}