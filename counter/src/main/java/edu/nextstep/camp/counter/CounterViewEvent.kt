package edu.nextstep.camp.counter

sealed class CounterViewEvent {
    object Up : CounterViewEvent()
    object Down : CounterViewEvent()
}