package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.domain.model.EvaluationRecord

class EvaluationHistoryAdapter : ListAdapter<EvaluationRecord, RecordViewHolder>(EvaluationHistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    internal class EvaluationHistoryDiffCallback : DiffUtil.ItemCallback<EvaluationRecord>() {
        override fun areItemsTheSame(oldItem: EvaluationRecord, newItem: EvaluationRecord): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: EvaluationRecord, newItem: EvaluationRecord): Boolean {
            return oldItem == newItem
        }
    }
}

class RecordViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(evaluationRecord: EvaluationRecord) {
        binding.tvExpression.text = evaluationRecord.expression
        binding.tvResult.text = binding.root.context.getString(R.string.evaluation_result, evaluationRecord.result)
    }
}
