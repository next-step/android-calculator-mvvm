package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.data.CalculationHistoryEntity
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class HistoryViewHolder(
    private val binding: ItemResultBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(history: CalculationHistoryEntity) {
        binding.tvExpression.text = history.expression.toString()
        binding.tvResult.text = history.result
    }

    companion object {
        fun create(parent: ViewGroup) = HistoryViewHolder(
            ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

}