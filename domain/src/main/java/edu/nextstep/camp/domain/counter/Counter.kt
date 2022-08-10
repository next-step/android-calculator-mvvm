package edu.nextstep.camp.domain.counter

class Counter {
    var currentCount = 0
        private set

    fun countUp(): Int = ++currentCount

    fun countDown(): Int {
        if (currentCount == 0) {
            throw NegativeCountNotSupported()
        }

        return --currentCount
    }
}