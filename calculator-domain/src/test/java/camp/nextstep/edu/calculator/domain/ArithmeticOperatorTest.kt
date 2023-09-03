package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ArithmeticOperatorTest {

    @ParameterizedTest
    @ValueSource(strings = ["+", "-", "*", "/"])
    fun `사칙연산자 유효성검사`(param: String) {
        val actual = ArithmeticOperator.isArithmeticOperator(param)
        assertThat(actual).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["a", "b", "US", "UK", "KR"])
    fun `비 사칙연산자 유효성검사`(param: String) {
        val actual = ArithmeticOperator.isArithmeticOperator(param)
        assertThat(actual).isFalse()
    }

    @ParameterizedTest
    @CsvSource("3,6,9")
    fun `더하기 연산자 테스트`(operand1: Int, operand2: Int, expected: Int) {
        val actual = ArithmeticOperator.PLUS(operand1, operand2)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource("6,3,3")
    fun `빼기 연산자 테스트`(operand1: Int, operand2: Int, expected: Int) {
        val actual = ArithmeticOperator.MINUS(operand1, operand2)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource("3,6,18")
    fun `곱하기 연산자 테스트`(operand1: Int, operand2: Int, expected: Int) {
        val actual = ArithmeticOperator.MULTIPLY(operand1, operand2)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource("6,3,2")
    fun `나누기 연산자 테스트`(operand1: Int, operand2: Int, expected: Int) {
        val actual = ArithmeticOperator.DIVIDE(operand1, operand2)
        assertThat(actual).isEqualTo(expected)
    }
}
