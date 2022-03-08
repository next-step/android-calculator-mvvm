package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.domain.CalculatorHistoryData

class CalculatorHistoryAdapter : ListAdapter<CalculatorHistoryData, CalculatorHistoryAdapter.HistoryViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
            val data = getItem(position) as CalculatorHistoryData
            holder.bind(data)

    }


    class HistoryViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(calculatorHistoryList: CalculatorHistoryData) {
            binding.tvExpression.text = calculatorHistoryList.expression
            binding.tvResult.text = calculatorHistoryList.result
        }

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CalculatorHistoryData>() {
            override fun areItemsTheSame(oldItem: CalculatorHistoryData, newItem: CalculatorHistoryData) =
                oldItem.hashCode() == newItem.hashCode()

            override fun areContentsTheSame(oldItem: CalculatorHistoryData, newItem: CalculatorHistoryData) =
                oldItem == newItem


        }
    }


}