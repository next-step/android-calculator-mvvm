package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator.Companion.IS_NOT_OPERATOR
import edu.nextstep.camp.calculator.domain.Calculator.Companion.IS_NOT_OR_BLANK
import edu.nextstep.camp.calculator.domain.Calculator.Companion.WRONG_INPUT
import edu.nextstep.camp.calculator.domain.Operator.Companion.IS_NOT_DIVIDE_BY_ZERO
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CalculatorTest {

    private lateinit var calculator: Calculator

    @BeforeEach
    fun setup() {
        calculator = Calculator()
    }


    @Test
    fun `두수를 입력 받아 더하기`() {
        //when
        val actual = calculator.calculate("10 + 2")
        //then
        assertThat(actual).isEqualTo(12)
    }

    @Test
    fun `두수를 입력 받아 빼기`() {
        //when
        val actual = calculator.calculate("10 - 2")
        //then
        assertThat(actual).isEqualTo(8)
    }

    @Test
    fun `두수를 입력 받아 곱하기`() {
        //when
        val actual = calculator.calculate("10 * 2")
        //then
        assertThat(actual).isEqualTo(20)
    }

    @Test
    fun `두수를 입력 받아 나누기`() {
        //when
        val actual = calculator.calculate("10 / 2")
        //then
        assertThat(actual).isEqualTo(5)
    }

    @Test
    fun `0으로 나누는 경우 에러`() {
        //when
        val exception = Assert.assertThrows(ArithmeticException::class.java) {
            calculator.calculate("10 / 0")
        }
        //then
        assertThat(exception.message).isEqualTo(IS_NOT_DIVIDE_BY_ZERO)
    }

    @Test
    fun `입력값이 null인 경우 에러`() {
        //when
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate(null)
        }
        //then
        assertThat(exception.message).isEqualTo(IS_NOT_OR_BLANK)
    }

    @Test
    fun `입력값이 blank인 경우 에러`() {
        //when
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate("     ")
        }
        //then
        assertThat(exception.message).isEqualTo(IS_NOT_OR_BLANK)
    }

    @Test
    fun `사칙연산 기호가 아닌 경우`() {
        //when
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate("10 @ 2")
        }
        //then
        assertThat(exception.message).isEqualTo(IS_NOT_OPERATOR)
    }

    @Test
    fun `사칙연산 하기`() {
        //when
        val actual = calculator.calculate("1 + 2 / 3")
        //then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `나눗셈이나 곱셈 기호를 처음 입력하면 오류`() {
        //when
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate("/ 10 * 2")
        }
        //then
        assertThat(exception.message).isEqualTo(WRONG_INPUT)
    }

    @Test
    fun `나눗셈이나 곱셈 기호를 마지막애 입력 하면 오류`() {
        //when
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate("10 * 2 /")
        }
        //then
        assertThat(exception.message).isEqualTo(WRONG_INPUT)
    }
}
