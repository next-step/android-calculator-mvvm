package camp.nextstep.edu.calculator.domain

class Calculator {

    fun calculate(expression: ArithmeticExpression): Int {
        val expressionItemList = expression.getExpressionItemList()
        val digitQueue = ArrayDeque<Int>().apply {
            addAll(
                expression.getDigitPositionedList(expressionItemList).map { it.toInt() }
            )
        }
        val operatorQueue = ArrayDeque<String>().apply {
            addAll(expression.getOperatorPositionedList(expressionItemList))
        }

        var result = digitQueue.removeFirst()
        while (digitQueue.isNotEmpty() && operatorQueue.isNotEmpty()) {
            result = operate(operatorQueue.removeFirst(), result, digitQueue.removeFirst())
        }

        return result
    }

    private fun operate(operator: String, operand1: Int, operand2: Int): Int =
        ArithmeticOperator.values()
            .find { it.value == operator }
            ?.invoke(operand1, operand2)
            ?: operand1
}
