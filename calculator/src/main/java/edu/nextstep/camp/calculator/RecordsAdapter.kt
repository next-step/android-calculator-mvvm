package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nextstep.camp.calculator.data.Record
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class RecordsAdapter : ListAdapter<Record, RecordsAdapter.RecordsViewHolder>(diffCallback()) {

    class RecordsViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Record) {
            binding.tvExpression.text = item.expression
            binding.tvResult.text = binding.root
                .context
                .getString(R.string.calculator_result_format, item.result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordsViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        fun diffCallback() = object : DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(
                oldItem: Record,
                newItem: Record
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Record,
                newItem: Record
            ): Boolean = oldItem == newItem
        }
    }
}
