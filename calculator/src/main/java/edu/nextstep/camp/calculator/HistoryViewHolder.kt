package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import nextstep.edu.data.History

class HistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(calculateHistory: History) {
        binding.history = calculateHistory
    }

}