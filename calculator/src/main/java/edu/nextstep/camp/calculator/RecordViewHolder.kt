package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.model.RecordStatement

class RecordViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(recordStatement: RecordStatement) {
        binding.recordStatement = recordStatement
        binding.executePendingBindings()
    }
}
