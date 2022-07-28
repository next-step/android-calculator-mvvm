package edu.nextstep.camp.calculator.domain

data class Expression(
    private val values: List<Any> = emptyList()
) {
    operator fun plus(operand: Int): Expression {
        return when (val last = values.lastOrNull()) {
            is Operator -> Expression(values + operand)
            is Int -> Expression(values.dropLast(1) + "$last$operand".toInt())
            null -> Expression(listOf(operand))
            else -> throw IllegalStateException("Failed plus operand. last: $last")
        }
    }

    operator fun plus(operator: Operator): Expression {
        return when (val last = values.lastOrNull()) {
            is Operator -> Expression(values.dropLast(1) + operator)
            is Int -> Expression(values + operator)
            null -> EMPTY
            else -> throw IllegalStateException("Failed plus operator. last: $last")
        }
    }

    fun removeLast(): Expression {
        return when (val last = values.lastOrNull()) {
            is Operator -> Expression(values.dropLast(1))
            is Int -> {
                val operand = (last / 10).takeIf { it != 0 }
                Expression(values.dropLast(1) + listOfNotNull(operand))
            }
            null -> EMPTY
            else -> throw IllegalStateException("Failed remove last. last: $last")
        }
    }

    override fun toString(): String {
        return values.joinToString(" ") {
            if (it is Operator) it.sign else it.toString()
        }
    }

    companion object {
        val EMPTY = Expression()
    }
}
