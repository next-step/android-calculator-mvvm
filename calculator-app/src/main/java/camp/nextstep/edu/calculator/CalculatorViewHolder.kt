package camp.nextstep.edu.calculator

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.Memory

class CalculatorViewHolder(
    private val binding: ItemResultBinding,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    init {
        initBinding()
    }

    private fun initBinding() {
        binding.lifecycleOwner = lifecycleOwner
    }

    fun bind(memory: Memory) {
        binding.memory = memory
    }
}