package com.example.hackathon22.triage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon22.databinding.TriageItemBinding

class TriageAdapter (
    val onItemClicked: (TriageModel) -> Unit
): ListAdapter<TriageModel, TriageViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriageViewHolder =
        TriageViewHolder(TriageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TriageViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { onItemClicked(getItem(position)) }
    }
}

class TriageViewHolder(private val binding: TriageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: TriageModel) {
        binding.icon.setImageResource(item.imageRes)
        binding.name.text = item.name
    }
}

class DiffCallback: DiffUtil.ItemCallback<TriageModel>() {
    override fun areItemsTheSame(oldItem: TriageModel, newItem: TriageModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: TriageModel, newItem: TriageModel): Boolean {
        return oldItem == newItem
    }

}