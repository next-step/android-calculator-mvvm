package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CalculatorTest {

    @ParameterizedTest
    @ValueSource(strings = ["2 + 3 * 4 / 2", "10 - 4 / 3 * 5"])
    fun `수식이 입력되면, 올바르게 계산한다`(expression: String) {
        // given
        val expected = 10

        // when
        val actual = Calculator.calculate(ArithmeticExpression(expression))

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
