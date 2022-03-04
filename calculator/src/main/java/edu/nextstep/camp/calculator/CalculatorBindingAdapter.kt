package edu.nextstep.camp.calculator

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.data.History

object CalculatorBindingAdapter {
    @JvmStatic
    @BindingAdapter("historyList")
    fun setHistoryList(recyclerView:RecyclerView, list: List<History>?) {
        (recyclerView.adapter as MainHistoryAdapter).submitList(list)
    }
}