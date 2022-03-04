package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.data.History
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class MainHistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(history: History) {
        binding.model = history
        binding.executePendingBindings()
    }
}