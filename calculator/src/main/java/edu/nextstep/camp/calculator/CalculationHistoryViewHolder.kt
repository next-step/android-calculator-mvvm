package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculationResult

class CalculationHistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: CalculationResult) {
        with(binding) {
            tvExpression.text = data.expression.toString()
            tvResult.text = "= ${data.result}"
        }
    }
}