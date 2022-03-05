package edu.nextstep.camp.calculator

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["setEnabledByViewType"])
fun setEnabledByViewType(button: Button, viewType: CalculatorViewType?) {
    button.isEnabled = if (viewType == null) true else viewType is ExpressionView
}

@BindingAdapter(value = ["setVisibilityByViewType"])
fun setVisibilityByViewType(textView: TextView, viewType: CalculatorViewType?) {
    textView.visibility = viewType?.let {
        if (it is ExpressionView) View.VISIBLE
        else View.INVISIBLE
    } ?: View.VISIBLE
}

@BindingAdapter(value = ["setVisibilityByViewType"])
fun setVisibilityByViewType(recyclerView: RecyclerView, viewType: CalculatorViewType?) {
    recyclerView.visibility = viewType?.let {
        if (it is ExpressionView) View.INVISIBLE
        else View.VISIBLE
    } ?: View.INVISIBLE
}