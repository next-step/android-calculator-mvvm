package edu.nextstep.camp.calculator

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun View.bindVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}
