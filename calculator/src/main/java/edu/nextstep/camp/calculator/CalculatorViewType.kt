package edu.nextstep.camp.calculator

sealed class CalculatorViewType {
    abstract fun toggle(): CalculatorViewType
}

object ExpressionView : CalculatorViewType() {
    override fun toString(): String = "ExpressionView"

    override fun toggle(): CalculatorViewType = MemoryView
}

object MemoryView : CalculatorViewType() {
    override fun toString(): String = "MemoryView"

    override fun toggle(): CalculatorViewType = ExpressionView
}