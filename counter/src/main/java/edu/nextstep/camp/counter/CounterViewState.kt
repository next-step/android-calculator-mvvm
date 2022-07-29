package edu.nextstep.camp.counter

sealed class CounterViewState {
    data class Counted(val counteredNumber: Int) : CounterViewState()
    object EMPTY : CounterViewState()
    object ZeroDownError : CounterViewState()
}