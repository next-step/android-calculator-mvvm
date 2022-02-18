package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @BeforeEach
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun `더하기`() {
        // when
        val actual = calculator.calculate("1 + 2")

        // then
        Truth.assertThat(actual).isEqualTo(3)
    }

    @Test
    fun `빼기`() {
        // when
        val actual = calculator.calculate("1 - 2")

        // then
        Truth.assertThat(actual).isEqualTo(-1)
    }

    @Test
    fun `곱하기`() {
        // when
        val actual = calculator.calculate("1 * 2")

        // then
        Truth.assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `나누기`() {
        // when
        val actual = calculator.calculate("4 / 2")

        // then
        Truth.assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `수식이 아니면 계산 불가`() {
        // when
        val actual = calculator.calculate("qwe")

        // then
        Truth.assertThat(actual).isNull()
    }

    @Test
    fun `미완성된 수식이면 계산 불가`() {
        // when
        val actual = calculator.calculate("1 +")

        // then
        Truth.assertThat(actual).isNull()
    }
}
