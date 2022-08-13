package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.domain.calculator.CalculationHistory

class CalculationHistoryAdapter: ListAdapter<CalculationHistory, CalculationHistoryAdapter.CalculationHistoryViewHolder>(CalculationHistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculationHistoryViewHolder {
        return CalculationHistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CalculationHistoryViewHolder, position: Int) {
        holder.showCalculationHistory(getItem(position))
    }

    class CalculationHistoryViewHolder private constructor(private val binding: ItemResultBinding): RecyclerView.ViewHolder(binding.root) {

        fun showCalculationHistory(calculationHistory: CalculationHistory) {
            binding.tvExpression.text = calculationHistory.expressionText
            binding.tvResult.text = binding.root.resources.getString(R.string.result, calculationHistory.result)
        }

        companion object {
            fun create(parent: ViewGroup): CalculationHistoryViewHolder {
                val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return CalculationHistoryViewHolder(binding)
            }
        }
    }
}

private class CalculationHistoryDiffCallback: DiffUtil.ItemCallback<CalculationHistory>() {
    override fun areItemsTheSame(oldItem: CalculationHistory, newItem: CalculationHistory): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CalculationHistory, newItem: CalculationHistory): Boolean {
        return oldItem == newItem
    }
}