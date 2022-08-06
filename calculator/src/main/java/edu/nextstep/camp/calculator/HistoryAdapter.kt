package edu.nextstep.camp.calculator

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.data.CalculationHistoryEntity

class HistoryAdapter : ListAdapter<CalculationHistoryEntity, HistoryViewHolder>(
    object : DiffUtil.ItemCallback<CalculationHistoryEntity>() {
        override fun areItemsTheSame(
            oldItem: CalculationHistoryEntity,
            newItem: CalculationHistoryEntity
        ): Boolean =
            oldItem.expression == newItem.expression

        override fun areContentsTheSame(
            oldItem: CalculationHistoryEntity,
            newItem: CalculationHistoryEntity
        ): Boolean =
            oldItem == newItem
    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HistoryViewHolder.create(parent)

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}