package camp.nextstep.edu.calculator.recyclerview

import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.BR
import camp.nextstep.edu.calculator.domain.model.CalculatorResult


class ResultViewHolder(
    private val binding: ItemResultBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: CalculatorResult) {
        binding.setVariable(BR.result, item)
        binding.executePendingBindings()
    }

    companion object {

        fun newInstance(
            binding: ItemResultBinding
        ) = ResultViewHolder(binding)
    }
}
