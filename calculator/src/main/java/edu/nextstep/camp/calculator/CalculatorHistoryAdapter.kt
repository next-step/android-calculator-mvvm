package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculateHistory

class CalculatorHistoryAdapter : ListAdapter<CalculateHistory, CalculatorHistoryViewHolder>(diffUtil) {
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
        val diffUtil = object : DiffUtil.ItemCallback<CalculateHistory>() {
            override fun areItemsTheSame(
                oldItem: CalculateHistory,
                newItem: CalculateHistory,
            ): Boolean = oldItem.expression == newItem.expression

            override fun areContentsTheSame(
                oldItem: CalculateHistory,
                newItem: CalculateHistory,
            ): Boolean = oldItem == newItem
        }
    }
}
