package edu.nextstep.camp.calculator

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class ViewHolder(binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
    val expression: TextView = binding.tvExpression
    val result: TextView = binding.tvResult
}