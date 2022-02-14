package edu.nextstep.camp.calculator

import android.widget.TextView
import androidx.databinding.BindingAdapter
import edu.nextstep.camp.calculator.domain.model.RecordStatement

object BindingUtils {
    @JvmStatic
    @BindingAdapter("expressionFormatted")
    fun setExpressionFormatted(textView: TextView, item: RecordStatement) {
        textView.text = item.expression
    }

    @JvmStatic
    @BindingAdapter("calculateResultFormatted")
    fun setCalculateResultFormatted(textView: TextView, item: RecordStatement) {
        textView.text = item.calculateResult.toString()
    }
}
