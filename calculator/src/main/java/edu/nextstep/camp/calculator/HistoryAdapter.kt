package edu.nextstep.camp.calculator

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.domain.History

class HistoryAdapter : ListAdapter<History, HistoryViewHolder>(
    object : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(
            oldItem: History,
            newItem: History
        ) = oldItem.expression == newItem.expression

        override fun areContentsTheSame(
            oldItem: History,
            newItem: History
        ) = oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HistoryViewHolder.create(parent)

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}