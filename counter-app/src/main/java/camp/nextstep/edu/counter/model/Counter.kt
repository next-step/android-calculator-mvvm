package camp.nextstep.edu.counter.model

class Counter {
    var value: Int = 0
        private set

    fun add() {
        value++
    }

    fun sub(): Boolean {
        value--
        if (isNegative()) {
            value = 0
            return false
        }
        return true
    }

    private fun isNegative(): Boolean {
        return value < 0
    }
}