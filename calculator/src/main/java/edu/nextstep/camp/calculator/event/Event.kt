package edu.nextstep.camp.calculator.event

sealed class Event {
    data class Error(val message: String): Event()
}
