package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.domain.calculator.model.CalculatorRecord

class CalculatorMemoryAdapter :
    ListAdapter<CalculatorRecord, CalculatorMemoryAdapter.ViewHolder>(MemoryDiffCallbackUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemResultBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CalculatorRecord) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    private class MemoryDiffCallbackUtil : DiffUtil.ItemCallback<CalculatorRecord>() {

        override fun areItemsTheSame(
            oldItem: CalculatorRecord,
            newItem: CalculatorRecord
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CalculatorRecord,
            newItem: CalculatorRecord
        ): Boolean = oldItem == newItem
    }
}
