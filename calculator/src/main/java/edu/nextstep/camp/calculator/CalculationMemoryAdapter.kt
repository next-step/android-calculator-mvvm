package edu.nextstep.camp.calculator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.data.CalculationRecordItem
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

/**
 * 계산기록리스트 adapter
 * Created by jeongjinhong on 2022. 07. 24..
 */
class CalculationMemoryAdapter() :
    ListAdapter<CalculationRecordItem, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemResultBinding =
            ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(itemResultBinding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) {
            holder.setData(getItem(holder.adapterPosition))
        }
    }

    class Holder(
        private val binding: ItemResultBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: CalculationRecordItem) {
            binding.tvExpression.text = data.expression
            binding.tvResult.text = "= ${data.result}"
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<CalculationRecordItem>() {
        override fun areItemsTheSame(
            oldItem: CalculationRecordItem,
            newItem: CalculationRecordItem
        ): Boolean {
            return oldItem.expression == newItem.expression
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: CalculationRecordItem,
            newItem: CalculationRecordItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}