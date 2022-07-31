package edu.nextstep.camp.calculator.memoryview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.memoryview.MemoryDiffCallback
import edu.nextstep.camp.calculator.memoryview.MemoryUIModel
import edu.nextstep.camp.calculator.memoryview.MemoryViewHolder

class MemoryRecyclerViewAdapter : ListAdapter<MemoryUIModel, MemoryViewHolder>(MemoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding: ItemResultBinding = ItemResultBinding.inflate(inflate, parent, false)
        return MemoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        val item = if (position >= itemCount) return else getItem(position)
        holder.bind(item)
    }
}