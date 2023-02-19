package camp.nextstep.edu.calculator

import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.model.CalculatorResultData

class CalculatorResultViewHolder(
    private val binding: ItemResultBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: CalculatorResultData) {
        binding.data = data
    }
}