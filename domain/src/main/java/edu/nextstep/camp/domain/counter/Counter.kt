package edu.nextstep.camp.domain.counter

class Counter(private var number: Int? = null) {
    fun up() = ((number ?: 0) + 1).also {
        number = it
    }

    fun down() = when (number) {
        0 -> throw IllegalArgumentException("You can not down under 0.")
        else -> maxOf(((number ?: 0) - 1), 0).also {
            number = it
        }
    }
}