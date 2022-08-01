package edu.nextstep.camp.counter.event

sealed class Event {
    data class Error(val message: String): Event()
}