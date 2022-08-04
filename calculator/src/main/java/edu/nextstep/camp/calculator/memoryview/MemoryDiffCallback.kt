package edu.nextstep.camp.calculator.memoryview

import androidx.recyclerview.widget.DiffUtil
import edu.nextstep.camp.calculator.memoryview.MemoryUIModel

class MemoryDiffCallback : DiffUtil.ItemCallback<MemoryUIModel>() {
    override fun areItemsTheSame(oldItem: MemoryUIModel, newItem: MemoryUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MemoryUIModel, newItem: MemoryUIModel): Boolean {
        return oldItem == newItem
    }
}