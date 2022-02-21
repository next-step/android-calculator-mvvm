package edu.nextstep.camp.calculator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.data.Memory

class MemoryAdapter : RecyclerView.Adapter<MemoryAdapter.ViewHolder>() {
    private var memories: List<Memory> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun refreshMemories(memories: List<Memory>) {
        this.memories = memories
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = memories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvExpression.text = memories[position].expression
        holder.tvResult.text = "= ${memories[position].result}"
    }

    inner class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        private val binding = ItemResultBinding.bind(containerView)
        val tvExpression: TextView = binding.tvExpression
        val tvResult: TextView = binding.tvResult
    }
}