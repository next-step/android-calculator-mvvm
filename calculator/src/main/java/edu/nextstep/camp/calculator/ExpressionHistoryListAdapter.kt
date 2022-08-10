package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.history.History

class ExpressionHistoryListAdapter :
    ListAdapter<History, ExpressionHistoryViewHolder>(HistoryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpressionHistoryViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpressionHistoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ExpressionHistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    private class HistoryDiffUtil : DiffUtil.ItemCallback<History>() {
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

}