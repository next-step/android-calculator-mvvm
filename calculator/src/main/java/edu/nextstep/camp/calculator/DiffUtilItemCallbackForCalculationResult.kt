package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.DiffUtil
import edu.nextstep.camp.calculator.domain.CalculationResult

class DiffUtilItemCallbackForCalculationResult(
) : DiffUtil.ItemCallback<CalculationResult>() {
    override fun areItemsTheSame(oldItem: CalculationResult, newItem: CalculationResult): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CalculationResult,
        newItem: CalculationResult
    ): Boolean {
        return oldItem == newItem
    }
}
