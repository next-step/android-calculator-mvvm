package edu.nextstep.camp.domain.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class OperatorTest {

    @Test
    internal fun `덧셈`() {
        val expected = 3

        val actual = Operator.Plus.operation(1, 2)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `나눗셈`() {
        val expected = 2

        val actual = Operator.Divide.operation(4, 2)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `뺄셈`() {
        val expected = -1

        val actual = Operator.Minus.operation(1, 2)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `곱셈`() {
        val expected = 2

        val actual = Operator.Multiply.operation(1, 2)

        assertThat(actual).isEqualTo(expected)
    }
}