package edu.nextstep.camp.calculator.memory

import androidx.recyclerview.widget.DiffUtil
import edu.nextstep.camp.calculator.domain.Memory

object MemoryDiffUtil : DiffUtil.ItemCallback<Memory.Item>() {

    override fun areItemsTheSame(oldItem: Memory.Item, newItem: Memory.Item) = oldItem.expression == newItem.expression

    override fun areContentsTheSame(oldItem: Memory.Item, newItem: Memory.Item) = oldItem == newItem
}