package edu.nextstep.camp.counter

import androidx.annotation.StringRes

enum class CounterEventData(@StringRes val stringId: Int) {
    CAN_NOT_DECREASE_DOWN_ZERO(R.string.str_can_not_down_zero)
}