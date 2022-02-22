package edu.nextstep.camp.calculator

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("recordItems")
fun RecyclerView.setRecordItems(items: List<CalculatorRecordUiState>?) {
    (this.adapter as? CalculatorMemoryAdapter)?.submitList(items ?: listOf()) ?: return
}
