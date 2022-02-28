package edu.nextstep.camp.calculator.domain

data class Memory(
    val expression: String,
    val result: String,
    val id: Int = 0
)
