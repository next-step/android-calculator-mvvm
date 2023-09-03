package camp.nextstep.edu.calculator.domain

import org.junit.Assert.*
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ArithmeticExpressionTest {

    @ParameterizedTest
    @ValueSource(strings = ["1 + + 2 + 3", "+ 2 + 3", "1 + 2 +", "1 a 2 +"])
    fun `올바르지 않은 수식 유효성 검사`(expression: String) {
        assertThrows(IllegalArgumentException::class.java) { ArithmeticExpression(expression) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1 + 2 + 3 + 5", "1 - 4 * 2 / 3"])
    fun `올바른 유효성 검사`(expression: String) {
        assertDoesNotThrow {
            ArithmeticExpression(expression)
        }
    }
}
