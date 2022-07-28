package edu.nextstep.camp.calculator.domain

data class ExpressionHistoryItem(
    val rawExpression: String,
    val result: Int
)