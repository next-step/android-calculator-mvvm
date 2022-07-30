package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculateHistory

class CalculatorHistoryAdapter: RecyclerView.Adapter<CalculatorResultViewHolder>() {
    private val calculatorHistories = mutableListOf<CalculateHistory>()

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
        holder.onBind(calculatorHistories[position])
    }

    override fun getItemCount(): Int = calculatorHistories.size

    fun setCalculatorHistories(calculatorHistories: List<CalculateHistory>) {
        this.calculatorHistories.clear()
        this.calculatorHistories.addAll(calculatorHistories)
        notifyDataSetChanged()
    }
}

class CalculatorResultViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(calculateResult: CalculateHistory) {
        binding.tvExpression.text = calculateResult.expression.toString()
        binding.tvResult.text = binding.root.resources.getString(R.string.calculate_result, calculateResult.result)
    }
}
