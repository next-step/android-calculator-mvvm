package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.DiffUtil
import edu.nextstep.camp.calculator.data.History

class HistoryDiffUtilCallback : DiffUtil.ItemCallback<History>() {
    override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem == newItem
    }
}