package edu.nextstep.camp.calculator

sealed class Event {
    object CalculatorError : Event()
}