package edu.nextstep.camp.calculator

import androidx.databinding.BindingConversion
import edu.nextstep.camp.calculator.domain.Operator

@BindingConversion
fun convertStringToInt(operand: String): Int = operand.toInt()

@BindingConversion
fun convertStringToOperator(operator: String): Operator? {
    val operatorStr =
        when(operator) {
            "÷" -> "/"
            "×" -> "*"
            else -> operator
        }
    return Operator.of(operatorStr)
}
