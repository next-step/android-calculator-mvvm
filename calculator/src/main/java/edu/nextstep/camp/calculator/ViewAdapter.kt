package edu.nextstep.camp.calculator

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["setEnabledByViewType"])
fun setEnabledByViewType(button: Button, viewType: CalculatorViewType?) {
    if (viewType == null) return
    button.isEnabled = viewType is ExpressionView
}

@BindingAdapter(value = ["setCalculatorVisibilityByViewType"])
fun setCalculatorVisibilityByViewType(textView: TextView, viewType: CalculatorViewType?) {
    if (viewType == null) {
        textView.visibility = View.VISIBLE
        return
    }

    textView.visibility = if (viewType is ExpressionView) View.VISIBLE
    else View.INVISIBLE
}

@BindingAdapter(value = ["setMemoryVisibilityByViewType"])
fun setMemoryVisibilityByViewType(recyclerView: RecyclerView, viewType: CalculatorViewType?) {
    if (viewType == null) {
        recyclerView.visibility = View.INVISIBLE
        return
    }

    recyclerView.visibility = if (viewType is ExpressionView) View.INVISIBLE
    else View.VISIBLE
}