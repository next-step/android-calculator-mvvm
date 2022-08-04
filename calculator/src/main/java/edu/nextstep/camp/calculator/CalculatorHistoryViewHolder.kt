package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculateResult

class CalculatorHistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(calculateResult: CalculateResult) {
        binding.tvExpression.text = calculateResult.expression.toString()
        binding.tvResult.text =
            binding.root.resources.getString(R.string.calculate_result, calculateResult.result)
    }
}
