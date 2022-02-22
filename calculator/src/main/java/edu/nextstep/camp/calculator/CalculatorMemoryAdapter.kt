package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class CalculatorMemoryAdapter :
    ListAdapter<CalculatorRecordUiState, CalculatorMemoryAdapter.RecordViewHolder>(
        RecordDiffUtilCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemResultBinding.inflate(layoutInflater, parent, false)
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holderRecord: RecordViewHolder, position: Int) {
        holderRecord.binding.uiState = getItem(position)
    }

    class RecordViewHolder(val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root)

    private class RecordDiffUtilCallback : DiffUtil.ItemCallback<CalculatorRecordUiState>() {
        override fun areItemsTheSame(
            oldItem: CalculatorRecordUiState,
            newItem: CalculatorRecordUiState
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CalculatorRecordUiState,
            newItem: CalculatorRecordUiState
        ): Boolean {
            return oldItem == newItem
        }
    }
}
