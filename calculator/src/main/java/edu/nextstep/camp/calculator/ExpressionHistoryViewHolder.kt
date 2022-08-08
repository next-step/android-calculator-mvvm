package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.data.historyStorage.HistoryEntity
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import java.util.*

class ExpressionHistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: HistoryEntity) {
        binding.history = item
    }
}