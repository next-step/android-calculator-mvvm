package edu.nextstep.camp.calculator.domain

data class Memory(
    val expression: Expression,
    val result: Int,
    val id: Int = 0
)
