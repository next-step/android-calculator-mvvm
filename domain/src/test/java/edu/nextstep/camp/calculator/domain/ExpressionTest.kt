package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

class ExpressionTest {
    @Test
    fun `빈 수식일 때, 피연산자를 추가할 수 있어야한다`() {
        // given
        val expression = Expression.EMPTY

        // when
        val actual = expression + 1

        // then
        Truth.assertThat(actual.toString()).isEqualTo("1")
    }

    @Test
    fun `'8' 수식이 있을 때, 9를 입력하면 89로 바뀌어야 한다`() {
        // given
        val expression = Expression(listOf(8))

        // when
        val actual = expression + 9

        // then
        Truth.assertThat(actual.toString()).isEqualTo("89")
    }

    @Test
    fun `빈 수식일 때, + 연산자를 추가할 수 없어야 한다`() {
        // given
        val expression = Expression.EMPTY

        // when
        val actual = expression + Operator.Plus

        // then
        Truth.assertThat(actual.toString()).isEqualTo("")
    }

    @Test
    fun `'1' 수식이 있을 때, + 연산자를 추가할 수 있어야 한다`() {
        // given
        val expression = Expression(listOf(1))

        // when
        val actual = expression + Operator.Plus

        // then
        Truth.assertThat(actual.toString()).isEqualTo("1 +")
    }

    @Test
    fun `'8 +' 수식이 있을 때, + 연산자를 - 연산자로 변경할 수 있어야 한다`() {
        // given
        val expression = Expression(listOf(8, Operator.Plus))

        // when
        val actual = expression + Operator.Minus

        // then
        Truth.assertThat(actual.toString()).isEqualTo("8 -")
    }

    @Test
    fun `'32 + 1' 수식이 있을 때, 마지막 1을 제거할 수 있어야 한다`() {
        // given
        val expression = Expression(listOf(32, Operator.Plus, 1))

        // when
        val actual = expression.removeLast()

        // then
        Truth.assertThat(actual.toString()).isEqualTo("32 +")
    }

    @Test
    fun `'32 +' 수식이 있을 때, 마지막 +를 제거할 수 있어야 한다`() {
        // given
        val expression = Expression(listOf(32, Operator.Plus))

        // when
        val actual = expression.removeLast()

        // then
        Truth.assertThat(actual.toString()).isEqualTo("32")
    }

    @Test
    fun `'32' 수식이 있을 때, 마지막 2를 제거할 수 있어야 한다`() {
        // given
        val expression = Expression(listOf(32))

        // when
        val actual = expression.removeLast()

        // then
        Truth.assertThat(actual.toString()).isEqualTo("3")
    }

    @Test
    fun `'3' 수식이 있을 때, 마지막 3을 제거할 수 있어야 한다`() {
        // given
        val expression = Expression(listOf(3))

        // when
        val actual = expression.removeLast()

        // then
        Truth.assertThat(actual.toString()).isEqualTo("")
    }

    @Test
    fun `빈 수식일 때, 마지막을 제거해도 빈 수식이어야 한다`() {
        // given
        val expression = Expression.EMPTY

        // when
        val actual = expression.removeLast()

        // then
        Truth.assertThat(actual.toString()).isEqualTo("")
    }
}
