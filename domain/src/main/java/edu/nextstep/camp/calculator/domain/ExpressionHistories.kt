package edu.nextstep.camp.calculator.domain

data class ExpressionHistories(
    val histories: List<ExpressionHistory>
) {

    fun add(history: ExpressionHistory): ExpressionHistories {
        return ExpressionHistories(histories + history)
    }

    companion object {
        val EMPTY = ExpressionHistories(emptyList())
    }
}