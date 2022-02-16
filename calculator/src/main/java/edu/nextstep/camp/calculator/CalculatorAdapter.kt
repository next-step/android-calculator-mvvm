package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class CalculatorAdapter : RecyclerView.Adapter<CalculatorAdapter.ViewHolder>() {

    private val items = mutableListOf<Memory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun replaceAll(items: List<Memory>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Memory) {
            binding.model = data
        }
    }
}
