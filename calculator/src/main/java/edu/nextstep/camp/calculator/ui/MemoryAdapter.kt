package edu.nextstep.camp.calculator.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.R
import edu.nextstep.camp.data.Memory

class MemoryAdapter : RecyclerView.Adapter<MemoryViewHolder>() {
    private val memories: MutableList<Memory> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun refreshMemories(memories: List<Memory>) {
        this.memories.clear()
        this.memories.addAll(memories)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = memories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return MemoryViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        holder.tvExpression.text = memories[position].expression
        holder.tvResult.text = "= ${memories[position].result}"
    }
}