package edu.nextstep.camp.calculator.memory

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.Memory

class MemoryViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Memory.Item) {
        binding.item = item
        binding.executePendingBindings()
    }
}
