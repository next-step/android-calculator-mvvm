package camp.nextstep.edu.calculator

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.Result

class ResultViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result) {
        binding.result = result
    }
}

class ResultItemDiffer : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem == newItem
}
