package edu.nextstep.camp.calculator

import androidx.annotation.StringRes

enum class CalculatorEvent(@StringRes val stringId: Int) {
    ERROR_INCOMPLETE_EXPRESSION(R.string.incomplete_expression)
}