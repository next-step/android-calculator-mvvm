package edu.nextstep.camp.domain.counter

class Counter(val number: Int = 0) {
    fun up() = Counter(number + 1)

    fun down() = when (number) {
        0 -> throw IllegalArgumentException("You can not down under 0.")
        else -> Counter(number - 1)
    }
}