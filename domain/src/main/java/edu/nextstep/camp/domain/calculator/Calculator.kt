package edu.nextstep.camp.domain.calculator

class Calculator {
    fun calculate(rawExpression: String): Int? {
        if (rawExpression.isBlank()) return null

        val values = rawExpression.split(" ")
        if (values.size % 2 == 0) return null

        var acc = values[0].toIntOrNull() ?: return null
        for (i in 1..values.lastIndex step 2) {
            val operator = Operator.of(values[i])
            val secondOperand = values[i + 1].toIntOrNull() ?: return null
            runCatching { operator.operation(acc, secondOperand) }
                .onSuccess { acc = it }
                .onFailure { return null }
        }
        return acc
    }
}
