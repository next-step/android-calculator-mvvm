package camp.nextstep.edu.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.model.Record

class CalculatorAdapter : ListAdapter<Record, CalculatorAdapter.CalculatorViewHolder>(diffUtil) {
    inner class CalculatorViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: Record) {
            binding.tvExpression.text = record.statement
            binding.tvResult.text = "= ${record.result}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculatorViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalculatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalculatorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}