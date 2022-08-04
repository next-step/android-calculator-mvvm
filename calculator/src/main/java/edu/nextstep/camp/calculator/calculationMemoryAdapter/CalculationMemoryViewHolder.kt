package edu.nextstep.camp.calculator.calculationMemoryAdapter

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculationRecord

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 01..
 */
class CalculationMemoryViewHolder(
    private val binding: ItemResultBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun setData(data: CalculationRecord) {
        binding.tvExpression.text = data.expression
        binding.tvResult.text = "= ${data.result}"
    }
}