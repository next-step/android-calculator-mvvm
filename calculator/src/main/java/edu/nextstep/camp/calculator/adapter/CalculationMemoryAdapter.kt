package edu.nextstep.camp.calculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.data.CalculationMemory
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class CalculationMemoryAdapter :
    ListAdapter<CalculationMemory, CalculationMemoryAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val memory = getItem(position)
        holder.binding.memory = memory
        holder.binding.executePendingBindings()
    }

    object DiffCallback : DiffUtil.ItemCallback<CalculationMemory>() {
        override fun areItemsTheSame(
            oldItem: CalculationMemory,
            newItem: CalculationMemory
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CalculationMemory,
            newItem: CalculationMemory
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root)

}