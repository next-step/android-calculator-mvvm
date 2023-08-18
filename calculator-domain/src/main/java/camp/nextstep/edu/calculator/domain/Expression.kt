package camp.nextstep.edu.calculator.domain

class Expression(value: String) {
    var value: String = value
        private set

    fun setOperand(operand: String) {
        value += operand
    }

    fun setOperator(operator: String) {
        if (value.isEmpty()) return

        if (isLastStringOperand()) {
            value += " $operator "
            return
        }

        if (isLastStringOperator()) {
            value = value.dropLast(OPERATOR_CONCAT_STRING_LENGTH) + " $operator "
        }
    }

    fun setEquals(result: String) {
        value = result
    }

    fun setDelete() {
        if (value.isEmpty()) return

        if (isLastStringOperand()) {
            value = value.dropLast(OPERAND_CONCAT_STRING_LENGTH)
            return
        }

        if (isLastStringOperator()) {
            value = value.dropLast(OPERATOR_CONCAT_STRING_LENGTH)
        }
    }

    private fun isLastStringOperator() =
        ArithmeticOperator.isArithmeticOperator(value.trimEnd().last().toString())

    private fun isLastStringOperand() = value.last().isDigit()

    companion object {
        private const val OPERAND_CONCAT_STRING_LENGTH = 1
        private const val OPERATOR_CONCAT_STRING_LENGTH = 3
    }
}
