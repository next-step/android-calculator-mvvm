package camp.nextstep.edu.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class ExpressionTest {
    @Test
    fun `마지막에 연산자가 입력된 상태에서 피연산자가 입력되면 기존 수식에 피연산자를 추가한다`() {
        // given
        val expression = Expression("1 + 3 + ")
        // when
        val actual = expression.addOperand(4)
        val expected = Expression("1 + 3 + 4")
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `마지막에 피연산자가 입력된 상태에서 피연산자가 입력되면 기존 수식에 피연산자를 추가한다`() {
        // given
        val expression = Expression("1 + 3")
        // when
        val actual = expression.addOperand(4)
        val expected = Expression("1 + 34")
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `수식이 비어있는 상태에서 연산자가 입력되면 아무런 값도 추가되지 않는다`() {
        // given
        val expression = Expression("")
        // when
        val actual = expression.addOperator(ArithmeticOperator.PLUS)
        val expected = Expression("")
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `피연산자가 있는 상태에서 연산자가 입력되면 기존 수식에 연산자를 추가한다`() {
        // given
        val expression = Expression("3")
        // when
        val actual = expression.addOperator(ArithmeticOperator.PLUS)
        val expected = Expression("3 + ")
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `연산자가 있는 상태에서 연산자가 입력되면 기존 수식에 있던 연산자를 지우고 입력된 연산자를 추가한다`() {
        // given
        val expression = Expression("3 + ")
        // when
        val actual = expression.addOperator(ArithmeticOperator.MINUS)
        val expected = Expression("3 - ")
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Equals가 입력되면 기존 수식에 계산한 값이 설정된다`() {
        // given
        val expression = Expression("1 + 2 + 3")
        // when
        val actual = expression.setEquals(6)
        val expected = Expression("6")
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `삭제가 입력되면 기존 수식의 마지막 값이 지워진다`() {
        // given
        val expression = Expression("1 + 2 + ")
        // when
        val actual = expression.setDelete()
        val expected = Expression("1 + 2")
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
