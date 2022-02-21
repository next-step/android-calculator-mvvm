package edu.nextstep.camp.calculator.memory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.R
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.model.Memories
import edu.nextstep.camp.calculator.domain.model.Memory

class MemoryAdapter : ListAdapter<Memory, MemoryViewHolder>(MemoryDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemResultBinding>(LayoutInflater.from(parent.context), R.layout.item_result, parent, false)
        return MemoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}