package camp.nextstep.edu.calculator.domain

data class Expression(val value: String) {

    fun addOperand(operand: Int) = this.copy(value = value + operand.toString())

    fun addOperator(operator: ArithmeticOperator): Expression =
        when {
            value.isEmpty() -> this
            isLastStringOperand() -> this.copy(value = value + " ${operator.value} ")
            isLastStringOperator() -> this.copy(value = value.dropLast(OPERATOR_CONCAT_STRING_LENGTH) + " ${operator.value} ")
            else -> this
        }

    fun setEquals(result: Int) = this.copy(value = result.toString())

    fun setDelete(): Expression =
        when {
            value.isEmpty() -> this
            isLastStringOperand() -> this.copy(value = value.dropLast(OPERAND_CONCAT_STRING_LENGTH))
            isLastStringOperator() -> this.copy(value = value.dropLast(OPERATOR_CONCAT_STRING_LENGTH))
            else -> this
        }

    private fun isLastStringOperator() =
        ArithmeticOperator.isArithmeticOperator(value.trimEnd().last().toString())

    private fun isLastStringOperand() = value.last().isDigit()

    companion object {
        private const val OPERAND_CONCAT_STRING_LENGTH = 1
        private const val OPERATOR_CONCAT_STRING_LENGTH = 3
    }
}
