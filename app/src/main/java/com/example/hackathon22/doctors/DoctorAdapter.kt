package com.example.hackathon22.doctors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon22.databinding.DoctorItemBinding

class DoctorAdapter (
    val onItemClicked: (DoctorModel) -> Unit
) : ListAdapter<DoctorModel, DoctorViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder =
        DoctorViewHolder(DoctorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { onItemClicked(getItem(position)) }
    }
}

class DoctorViewHolder(private val binding: DoctorItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DoctorModel) {
        binding.image.setImageResource(item.imageRes)
        binding.name.text = item.name
        binding.specialty.text = item.specialty
    }
}

class DiffCallback: DiffUtil.ItemCallback<DoctorModel>() {
    override fun areItemsTheSame(oldItem: DoctorModel, newItem: DoctorModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: DoctorModel, newItem: DoctorModel): Boolean {
        return oldItem == newItem
    }

}