package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.data.ResultRecord
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class ResultAdapter(private val results: MutableList<ResultRecord>) : RecyclerView.Adapter<ViewHolder>() {
    fun addResult(resultRecord: ResultRecord) {
        results.add(resultRecord)
    }

    fun getResult(): List<ResultRecord> {
        return results.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemResultBinding = ItemResultBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.expression.text = results[position].expression
        holder.result.text = results[position].result
    }

    override fun getItemCount(): Int {
        return results.size
    }
}