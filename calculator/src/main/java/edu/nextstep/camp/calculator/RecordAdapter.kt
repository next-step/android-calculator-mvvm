package edu.nextstep.camp.calculator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.model.RecordStatement

class RecordAdapter : ListAdapter<RecordStatement, RecordViewHolder>(DiffCallbackUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view: ItemResultBinding =
            ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) =
        holder.bind(getItem(position))

    class DiffCallbackUtil : DiffUtil.ItemCallback<RecordStatement>() {
        override fun areItemsTheSame(oldItem: RecordStatement, newItem: RecordStatement): Boolean =
            oldItem.uuid == newItem.uuid

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: RecordStatement,
            newItem: RecordStatement
        ): Boolean = oldItem == newItem
    }
}
