package edu.nextstep.camp.calculator

import android.widget.TextView
import androidx.databinding.BindingAdapter
import edu.nextstep.camp.calculator.domain.model.RecordStatement

@BindingAdapter("expressionFormatted")
fun TextView.setExpressionFormatted(item: RecordStatement) {
    text = item.expression
}

@BindingAdapter("calculateResultFormatted")
fun TextView.setCalculateResultFormatted(item: RecordStatement) {
    text = item.calculateResult.toString()
}