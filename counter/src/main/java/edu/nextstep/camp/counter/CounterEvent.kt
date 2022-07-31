package edu.nextstep.camp.counter

sealed class CounterEvent {
    object Up : CounterEvent()
    object Down : CounterEvent()
}