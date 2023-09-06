package camp.nextstep.edu.calculator

import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.CalculationResult

class CalculatorResultViewHolder(
    private val binding: ItemResultBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CalculationResult) {
        binding.expression = item.expression
        binding.result = item.result
    }
}