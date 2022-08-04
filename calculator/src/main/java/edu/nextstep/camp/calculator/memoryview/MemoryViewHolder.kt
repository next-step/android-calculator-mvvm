package edu.nextstep.camp.calculator.memoryview

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.memoryview.MemoryUIModel

class MemoryViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(memory: MemoryUIModel) {
        binding.tvExpression.text = memory.expressionText
        binding.tvResult.text = "= ${memory.resultText}"
    }
}