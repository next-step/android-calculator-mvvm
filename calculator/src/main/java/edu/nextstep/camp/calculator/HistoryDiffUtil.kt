package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.DiffUtil
import nextstep.edu.data.History

class HistoryDiffUtil : DiffUtil.ItemCallback<History>() {
    override fun areItemsTheSame(
        oldItem: History,
        newItem: History
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: History,
        newItem: History
    ): Boolean {
        return oldItem == newItem
    }
}