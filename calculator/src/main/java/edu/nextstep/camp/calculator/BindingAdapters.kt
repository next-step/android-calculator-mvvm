package edu.nextstep.camp.calculator

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import edu.nextstep.camp.calculator.domain.Operator

@BindingAdapter("calculatorViewModel", "operand")
fun addToExpression(view: View, calculatorViewModel: CalculatorViewModel, operand: Int) {
    view.setOnClickListener {
        calculatorViewModel.addToExpression(operand)
    }
}

@BindingAdapter("calculatorViewModel", "operator")
fun addToExpression(view: View, calculatorViewModel: CalculatorViewModel, operator: Operator) {
    view.setOnClickListener {
        calculatorViewModel.addToExpression(operator)
    }
}

