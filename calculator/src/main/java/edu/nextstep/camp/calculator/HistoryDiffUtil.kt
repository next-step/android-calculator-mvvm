package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.DiffUtil
import nextstep.edu.data.History

class HistoryDiffUtil : DiffUtil.ItemCallback<nextstep.edu.data.History>() {
    override fun areItemsTheSame(
        oldItem: nextstep.edu.data.History,
        newItem: nextstep.edu.data.History
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: nextstep.edu.data.History,
        newItem: nextstep.edu.data.History
    ): Boolean {
        return oldItem == newItem
    }
}