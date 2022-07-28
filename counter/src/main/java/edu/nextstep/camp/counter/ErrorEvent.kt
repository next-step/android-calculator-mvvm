package edu.nextstep.camp.counter

sealed class ErrorEvent {
    object CalculatorError : ErrorEvent()
}