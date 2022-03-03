package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.data.CalculatorHisotry

class CalculatorHistoryAdapter : ListAdapter<CalculatorHisotry, CalculatorHistoryAdapter.HistoryViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
            val data = getItem(position) as CalculatorHisotry
            holder.bind(data)

    }


    class HistoryViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(calculatorHistoryList: CalculatorHisotry) {
            binding.tvExpression.text = calculatorHistoryList.expression
            binding.tvResult.text = calculatorHistoryList.result
        }

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CalculatorHisotry>() {
            override fun areItemsTheSame(oldItem: CalculatorHisotry, newItem: CalculatorHisotry) =
                oldItem.hashCode() == newItem.hashCode()

            override fun areContentsTheSame(oldItem: CalculatorHisotry, newItem: CalculatorHisotry) =
                oldItem == newItem


        }
    }


}