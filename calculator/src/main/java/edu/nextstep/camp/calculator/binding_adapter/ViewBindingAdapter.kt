package edu.nextstep.camp.calculator.binding_adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
internal fun View.isVisible(visible: Boolean?) {
    if (visible == null) return
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}
