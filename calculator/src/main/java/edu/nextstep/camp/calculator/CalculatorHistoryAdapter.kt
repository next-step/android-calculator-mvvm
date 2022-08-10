package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculateHistory
import edu.nextstep.camp.calculator.domain.CalculateResult

class CalculatorHistoryAdapter : ListAdapter<CalculateResult, CalculatorHistoryViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculatorHistoryViewHolder {
        return CalculatorHistoryViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CalculatorHistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CalculateResult>() {
            override fun areItemsTheSame(
                oldItem: CalculateResult,
                newItem: CalculateResult,
            ): Boolean = oldItem.expression == newItem.expression

            override fun areContentsTheSame(
                oldItem: CalculateResult,
                newItem: CalculateResult,
            ): Boolean = oldItem == newItem
        }
    }
}
