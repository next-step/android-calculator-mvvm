package edu.nextstep.camp.calculator

sealed class Event private constructor() {
    object CalculationErrorEvent : Event()
}
