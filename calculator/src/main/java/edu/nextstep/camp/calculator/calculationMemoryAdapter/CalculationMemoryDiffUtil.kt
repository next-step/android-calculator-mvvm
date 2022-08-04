package edu.nextstep.camp.calculator.calculationMemoryAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import edu.nextstep.camp.calculator.domain.CalculationRecord

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 01..
 */
class CalculationMemoryDiffUtil : DiffUtil.ItemCallback<CalculationRecord>() {
    override fun areItemsTheSame(
        oldItem: CalculationRecord,
        newItem: CalculationRecord
    ): Boolean {
        return oldItem.expression == newItem.expression
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: CalculationRecord,
        newItem: CalculationRecord
    ): Boolean {
        return oldItem == newItem
    }
}