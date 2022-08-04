package edu.nextstep.camp.calculator.common

class Event<out T>(private val content: T) {
    var consumed = false
        private set

    fun consume(): T? {
        return if (consumed) {
            null
        } else {
            consumed = true
            content
        }
    }

    fun peek(): T = content
}
