package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.model.ExpressionHistory

class CalculatorHistoryAdapter :
    ListAdapter<ExpressionHistory, CalculatorHistoryAdapter.ViewHolder>(
        ExpressionHistoryDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result, parent, false)
            .let { view -> ViewHolder(view) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemResultBinding.bind(view)
        fun bind(item: ExpressionHistory) {
            val result = "= ${item.result}"
            binding.tvExpression.text = item.expression
            binding.tvResult.text = result
        }
    }
}

class ExpressionHistoryDiffCallback : DiffUtil.ItemCallback<ExpressionHistory>() {
    override fun areContentsTheSame(
        oldItem: ExpressionHistory,
        newItem: ExpressionHistory
    ) = oldItem == newItem

    override fun areItemsTheSame(
        oldItem: ExpressionHistory,
        newItem: ExpressionHistory
    ) = oldItem.expression == newItem.expression
}