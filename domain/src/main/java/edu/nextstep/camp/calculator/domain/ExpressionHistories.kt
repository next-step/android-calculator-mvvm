package edu.nextstep.camp.calculator.domain

class ExpressionHistories(
    vararg _histories: ExpressionHistory
) {
    private val histories: List<ExpressionHistory> = _histories.toList()
}