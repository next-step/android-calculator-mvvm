package edu.nextstep.camp.calculator.util

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun View.bindVisibility(isVisible: Boolean) {
    this.isVisible =  isVisible
}