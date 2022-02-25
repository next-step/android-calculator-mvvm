package edu.nextstep.camp.calculator

import androidx.databinding.BindingConversion
import edu.nextstep.camp.calculator.domain.Expression

@BindingConversion
fun convertExpressionToString(expression: Expression) = expression.toString()
