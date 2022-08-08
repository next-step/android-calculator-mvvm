package edu.nextstep.camp.calculator

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.data.historyStorage.HistoryEntity
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class ExpressionHistoryListAdapter :
    ListAdapter<HistoryEntity, ExpressionHistoryViewHolder>(HistoryEntityDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpressionHistoryViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpressionHistoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ExpressionHistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    private class HistoryEntityDiffUtil : DiffUtil.ItemCallback<HistoryEntity>() {
        override fun areItemsTheSame(
            oldItem: HistoryEntity,
            newItem: HistoryEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HistoryEntity,
            newItem: HistoryEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

}