package camp.nextstep.edu.calculator.domain

enum class ArithmeticOperator(val value: String) {

    PLUS("+") {
        override fun invoke(a: Int, b: Int) = a + b
    },
    MINUS("-") {
        override fun invoke(a: Int, b: Int) = a - b
    },
    MULTIPLY("*") {
        override fun invoke(a: Int, b: Int) = a * b
    },
    DIVIDE("/") {
        override fun invoke(a: Int, b: Int) = a / b
    };

    abstract operator fun invoke(a: Int, b: Int): Int

    companion object {
        fun isArithmeticOperator(param: String) =
            param in ArithmeticOperator.values().map { it.value }
    }
}
