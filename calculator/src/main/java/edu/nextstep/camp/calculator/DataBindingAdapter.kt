package edu.nextstep.camp.calculator

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("visibilityEvaluationView")
fun setVisibilityForEvaluationTextView(view: TextView, displayState: CalculatorViewModel.State) {
    view.isVisible = displayState == CalculatorViewModel.State.ShowExpression
}

@BindingAdapter("visibilityEvaluationHistoryView")
fun setVisibilityForEvaluationHistoryView(view: RecyclerView, displayState: CalculatorViewModel.State) {
    view.isVisible = displayState == CalculatorViewModel.State.ShowHistory
}
