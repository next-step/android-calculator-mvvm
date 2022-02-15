package edu.nextstep.camp.calculator.domain

data class Memory(val items: List<Item> = emptyList()) {

    operator fun plus(value: Item) = Memory(items + value)

    companion object {
        val EMPTY = Memory()
    }

    data class Item(val expression: String, val result: Int)
}