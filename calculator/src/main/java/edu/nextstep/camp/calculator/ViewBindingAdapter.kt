package edu.nextstep.camp.calculator

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("invisibleIf")
internal fun View.invisibleIf(invisible: Boolean?) {
    if (invisible == null) return
    visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}
