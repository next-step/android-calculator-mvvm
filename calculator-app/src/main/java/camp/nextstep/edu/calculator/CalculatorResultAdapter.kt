package camp.nextstep.edu.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.CalculationResult

class CalculatorResultAdapter :
    ListAdapter<CalculationResult, CalculatorResultViewHolder>(CalculatorResultDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CalculatorResultViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CalculatorResultViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private class CalculatorResultDiffer : DiffUtil.ItemCallback<CalculationResult>() {
        override fun areItemsTheSame(
            oldItem: CalculationResult,
            newItem: CalculationResult
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CalculationResult,
            newItem: CalculationResult
        ) = oldItem == newItem
    }
}