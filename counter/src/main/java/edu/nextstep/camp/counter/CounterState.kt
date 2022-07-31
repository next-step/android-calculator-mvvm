package edu.nextstep.camp.counter

sealed class CounterState {
    data class Counted(val counteredNumber: Int) : CounterState()
    object EMPTY : CounterState()
    object ZeroDownError : CounterState()
}