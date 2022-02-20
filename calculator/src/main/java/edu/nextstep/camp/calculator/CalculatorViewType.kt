package edu.nextstep.camp.calculator

sealed class CalculatorViewType {
    override fun toString(): String {
        return when (this) {
            is ExpressionView -> "Expression"
            is MemoryView -> "Memory"
        }
    }
}

object ExpressionView : CalculatorViewType()

object MemoryView : CalculatorViewType()

fun CalculatorViewType.toggle(): CalculatorViewType {
    return when (this) {
        is ExpressionView -> MemoryView
        is MemoryView -> ExpressionView
    }
}

fun CalculatorViewType.isExpression(): Boolean {
    return this is ExpressionView
}

fun CalculatorViewType.isMemory(): Boolean {
    return this is MemoryView
}