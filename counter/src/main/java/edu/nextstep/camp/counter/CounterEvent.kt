package edu.nextstep.camp.counter

sealed interface CounterEvent {

    object ShowNegativeError : CounterEvent
}
