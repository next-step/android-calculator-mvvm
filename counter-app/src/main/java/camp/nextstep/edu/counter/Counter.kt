package camp.nextstep.edu.counter

class Counter {
    var number = 0
        private set

    fun increment() {
        number++
    }

    fun decrement() {
        if (number > 0) {
            number--
            return
        }

        throw IllegalStateException("0 이하로 내릴 수 없습니다")
    }
}
