package edu.nextstep.camp.calculator

import edu.nextstep.camp.data.Memory

sealed class CalculatorViewType {
    abstract fun toggle(memories: List<Memory>?): CalculatorViewType
}

object ExpressionView : CalculatorViewType() {
    override fun toString(): String = "ExpressionView"

    override fun toggle(memories: List<Memory>?): CalculatorViewType = MemoryView(memories)
}

data class MemoryView(val memories: List<Memory>?) : CalculatorViewType() {
    override fun toString(): String = "MemoryView"

    override fun toggle(memories: List<Memory>?): CalculatorViewType = ExpressionView
}