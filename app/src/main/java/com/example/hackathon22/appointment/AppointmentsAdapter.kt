package com.example.hackathon22.appointment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon22.R
import com.example.hackathon22.databinding.AppointmentItemBinding
import com.example.hackathon22.doctors.DoctorModel
import java.util.*

class AppointmentsAdapter : ListAdapter<DoctorModel, AppointmentViewHolder>(DiffCallback()) {

    private val colors = listOf(R.color.light_green, R.color.light_blue, R.color.light_pink)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder =
        AppointmentViewHolder(AppointmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(getItem(position), generateRandomColor())
    }


    val mRandom: Random = Random(System.currentTimeMillis())

    fun generateRandomColor(): Int {
        // This is the base color which will be mixed with the generated one
        val baseColor: Int = Color.WHITE
        val baseRed: Int = Color.red(baseColor)
        val baseGreen: Int = Color.green(baseColor)
        val baseBlue: Int = Color.blue(baseColor)
        val red: Int = (baseRed + mRandom.nextInt(256)) / 2
        val green: Int = (baseGreen + mRandom.nextInt(256)) / 2
        val blue: Int = (baseBlue + mRandom.nextInt(256)) / 2
        return Color.rgb(red, green, blue)
    }
}

class AppointmentViewHolder(private val binding: AppointmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DoctorModel, color: Int) {
        binding.image.setImageResource(item.imageRes)
        binding.name.text = item.name
        binding.specialty.text = item.specialty
        binding.time.text = item.startTime
        binding.root.setCardBackgroundColor(color)
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