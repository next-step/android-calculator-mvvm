package camp.nextstep.edu.counter

class Counter {
    var number = 0
        private set

    fun increment() {
        number++
    }

    fun decrement() {
        check(number > 0) { "0 이하로 내릴 수 없습니다" }

        number--
    }
}
