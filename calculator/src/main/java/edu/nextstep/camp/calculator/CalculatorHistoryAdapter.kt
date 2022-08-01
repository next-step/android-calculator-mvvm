package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculateHistory

class CalculatorHistoryAdapter : ListAdapter<CalculateHistory, CalculatorResultViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculatorResultViewHolder {
        return CalculatorResultViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CalculatorResultViewHolder, position: Int) {
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

class CalculatorResultViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(calculateResult: CalculateHistory) {
        binding.tvExpression.text = calculateResult.expression.toString()
        binding.tvResult.text =
            binding.root.resources.getString(R.string.calculate_result, calculateResult.result)
    }
}
