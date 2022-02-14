package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
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
}
