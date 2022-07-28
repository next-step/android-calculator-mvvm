package edu.nextstep.camp.counter

import androidx.annotation.StringRes

enum class CounterEvent(@StringRes val id: Int) {
    CAN_NOT_DECREASE_COUNT_UNDER_0(R.string.counter_event_can_not_decrease_count_under_0)
}