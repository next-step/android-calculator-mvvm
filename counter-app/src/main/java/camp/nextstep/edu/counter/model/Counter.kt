package camp.nextstep.edu.counter.model

class Counter {
    var value = 0

    fun add() {
        value++
    }

    fun sub() {
        value--
        if (isNegative()) {
            value = 0
        }
    }

    fun isNegative(): Boolean {
        return value < 0
    }
}