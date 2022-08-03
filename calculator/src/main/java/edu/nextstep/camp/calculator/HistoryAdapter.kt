package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.History

/**
 * Created by link.js on 2022. 07. 23..
 */
class HistoryAdapter : ListAdapter<History, HistoryViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setItems(itemList: List<History>) {
        submitList(itemList)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<History>() {
            override fun areContentsTheSame(oldItem: History, newItem: History) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: History, newItem: History) =
                oldItem.expression == newItem.expression
        }
    }
}

class HistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(history: History) {
        binding.history = history
    }
}