package edu.nextstep.camp.calculator

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("app:calculatorUiMode")
fun RecyclerView.setCalculatorUiMode(calculatorUiMode: CalculatorUiMode) {
    visibility = when (calculatorUiMode) {
        CalculatorUiMode.CALCULATOR -> View.GONE
        CalculatorUiMode.CALCULATION_HISTORY -> View.VISIBLE
    }
}