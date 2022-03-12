package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.model.Memory

class MainHistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(history: Memory) {
        binding.model = history
        binding.executePendingBindings()
    }
}