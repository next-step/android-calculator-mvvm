package camp.nextstep.edu.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.Memory

class CalculatorAdapter(
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Memory, CalculatorViewHolder>(CalculatorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculatorViewHolder {
        return CalculatorViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false), lifecycleOwner)
    }

    override fun onBindViewHolder(holder: CalculatorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CalculatorDiffCallback : DiffUtil.ItemCallback<Memory>() {
    override fun areItemsTheSame(oldItem: Memory, newItem: Memory): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Memory, newItem: Memory): Boolean {
        return oldItem == newItem
    }
}
