package camp.nextstep.edu.counter.domain

@JvmInline
value class Counter(val number: Int = MINIMUM_NUMBER) {

    init {
        require(number >= MINIMUM_NUMBER) {
            "숫자는 0이상 이어야함"
        }
    }

    override fun toString(): String {
        return number.toString()
    }

    operator fun plus(i: Int): Counter {
        return Counter(number + i)
    }

    operator fun minus(i: Int): Counter {
        return Counter(number - i)
    }

    companion object {
        val ZERO = Counter()
        const val MINIMUM_NUMBER = 0
    }
}
