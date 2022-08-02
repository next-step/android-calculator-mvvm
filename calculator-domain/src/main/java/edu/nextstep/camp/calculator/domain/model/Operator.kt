package edu.nextstep.camp.calculator.domain.model

enum class Operator(
    val sign: String,
    val operation: (Int, Int) -> Int,
) {
    Plus("+", { x, y -> x + y }),
    Minus("-", { x, y -> x - y }),
    Multiply("×", { x, y -> x * y }),
    Divide("÷", { x, y -> x / y });

    companion object {
        fun of(sign: String): Operator? {
            return values().find { it.sign == sign }
        }
    }
}
