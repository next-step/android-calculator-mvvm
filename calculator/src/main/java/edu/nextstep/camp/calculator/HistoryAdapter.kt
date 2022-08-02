package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.History

class HistoryAdapter :
    ListAdapter<History, HistoryAdapter.HistoryViewHolder>(
        object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(
                oldItem: History,
                newItem: History
            ): Boolean =
                oldItem.expression == newItem.expression

            override fun areContentsTheSame(
                oldItem: History,
                newItem: History
            ): Boolean =
                oldItem == newItem
        }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class HistoryViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: History) {
            binding.history = item
        }
    }
}