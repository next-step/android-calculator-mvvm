package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class HistoryListAdapter : ListAdapter<HistoryItem, HistoryListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<HistoryItem>() {

        override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
            return oldItem.expression == newItem.expression
        }

        override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
            return oldItem == newItem
        }
    }
) {

    class ViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
