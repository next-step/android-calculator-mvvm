package edu.nextstep.camp.calculator

import edu.nextstep.camp.data.Memory

sealed class CalculatorViewType {
    override fun toString(): String {
        return when (this) {
            is ExpressionView -> "Expression"
            is MemoryView -> "Memory"
        }
    }
}

object ExpressionView : CalculatorViewType()

class MemoryView(val memories: List<Memory>?) : CalculatorViewType()

fun CalculatorViewType.toggle(memories: List<Memory>?): CalculatorViewType {
    return when (this) {
        is ExpressionView -> MemoryView(memories)
        is MemoryView -> ExpressionView
    }
}

fun CalculatorViewType.isExpression(): Boolean {
    return this is ExpressionView
}

fun CalculatorViewType.isMemory(): Boolean {
    return this is MemoryView
}