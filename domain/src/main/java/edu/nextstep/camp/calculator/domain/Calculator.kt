package edu.nextstep.camp.calculator.domain

interface Calculator {

    fun calculate(expression: StringExpressionState): Operand

    fun calculate(raw: String): Operand
}
