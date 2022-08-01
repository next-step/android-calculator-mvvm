package edu.nextstep.camp.calculator.binding_adapter

import android.view.View
import androidx.core.view.isInvisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
internal fun isVisible(view: View, visible: Boolean?) {
    if (visible == null) return
    view.isInvisible = !visible
}
