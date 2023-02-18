package com.example.domain.models

class Calculator(private val operationParser: OperationParser = OperationParser) {
    fun calculate(operation: String): Int {
        val terms = operationParser.parse(operation)

        require(isCompleteOperation(terms.size)) {
            "완성되지 않은 수식입니다."
        }

        var accumulator: Int = (terms[0] as Operand).value
        for (index: Int in 1 until terms.size step 2) {
            val operator: Operator = terms[index] as Operator
            val operand = terms[index + 1]

            accumulator = operator.execute(accumulator, (operand as Operand).value)
        }
        return accumulator
    }

    private fun isCompleteOperation(size: Int): Boolean {
        return size % 2 != 0
    }
}
