package edu.nextstep.camp.calculator.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class MemoryViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
    private val binding = ItemResultBinding.bind(containerView)
    val tvExpression: TextView = binding.tvExpression
    val tvResult: TextView = binding.tvResult
}