package edu.nextstep.camp.calculator

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Created by link.js on 2022. 07. 31..
 */

@BindingAdapter("app:isVisible")
fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}