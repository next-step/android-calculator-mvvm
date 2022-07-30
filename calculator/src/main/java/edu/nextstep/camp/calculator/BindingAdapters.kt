package edu.nextstep.camp.calculator

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:isVisible")
fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}