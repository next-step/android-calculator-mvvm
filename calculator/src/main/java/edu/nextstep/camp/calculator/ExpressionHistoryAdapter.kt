package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.ExpressionHistory

class ExpressionHistoryAdapter :
    ListAdapter<ExpressionHistory, ExpressionHistoryAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
            .let { ViewHolder(it) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemResultBinding.bind(view)

        fun bind(item: ExpressionHistory) {
            binding.item = item
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ExpressionHistory>() {
            override fun areItemsTheSame(
                oldItem: ExpressionHistory,
                newItem: ExpressionHistory
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ExpressionHistory,
                newItem: ExpressionHistory
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}