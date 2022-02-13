package edu.nextstep.camp.calculator.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.model.RecordStatement

class RecordAdapter : RecyclerView.Adapter<RecordViewHolder>() {
    private val statementList = mutableListOf<RecordStatement>()

    fun addStatement(recordStatement: RecordStatement) {
        statementList.add(0, recordStatement)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view: ItemResultBinding =
            ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(statementList[position])
    }

    override fun getItemCount(): Int = statementList.size
}