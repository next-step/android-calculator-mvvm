package edu.nextstep.camp.calculator.memory

import androidx.recyclerview.widget.DiffUtil
import edu.nextstep.camp.calculator.domain.model.Memories
import edu.nextstep.camp.calculator.domain.model.Memory

object MemoryDiffUtil : DiffUtil.ItemCallback<Memory>() {

    override fun areItemsTheSame(oldItem: Memory, newItem: Memory) = oldItem.expression == newItem.expression

    override fun areContentsTheSame(oldItem: Memory, newItem: Memory) = oldItem == newItem
}