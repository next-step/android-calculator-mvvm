package edu.nextstep.camp.calculator.calculationMemoryAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculationRecord

/**
 * 계산기록리스트 adapter
 * Created by jeongjinhong on 2022. 07. 24..
 */
class CalculationMemoryAdapter() :
    ListAdapter<CalculationRecord, RecyclerView.ViewHolder>(CalculationMemoryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemResultBinding =
            ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalculationMemoryViewHolder(itemResultBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CalculationMemoryViewHolder) {
            holder.setData(getItem(holder.adapterPosition))
        }
    }
}