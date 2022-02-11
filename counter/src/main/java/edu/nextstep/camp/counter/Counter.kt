package edu.nextstep.camp.counter

data class Counter(val number: Int) {
    fun plusCount(): Counter = Counter(number + 1)
    fun minusCount(): Counter =  Counter(number - 1)

    override fun toString(): String {
        return number.toString()
    }
}