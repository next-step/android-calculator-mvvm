package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class OperatorTest {

    @Test
    @DisplayName("+ 기호로 연산자를 만들면 Plus이어야 한다")
    fun creationOperatorPlus() {
        // when
        val operator = Operator.of("+")

        // then
        assertThat(operator).isEqualTo(Operator.Plus)
    }

    @Test
    @DisplayName("- 기호로 연산자를 만들면 Minus이어야 한다")
    fun creationOperatorMinus() {
        // when
        val operator = Operator.of("-")

        // then
        assertThat(operator).isEqualTo(Operator.Minus)    }

    @Test
    @DisplayName("* 기호로 연산자를 만들면 Multiply이어야 한다")
    fun creationOperatorMultiply() {
        // when
        val operator = Operator.of("*")

        // then
        assertThat(operator).isEqualTo(Operator.Multiply)
    }

    @Test
    @DisplayName("/ 기호로 연산자를 만들면 Divide이어야 한다")
    fun creationOperatorDivide() {
        // when
        val operator = Operator.of("/")

        // then
        assertThat(operator).isEqualTo(Operator.Divide)
    }

    @Test
    @DisplayName("정의되지 않은 기호로 연산자를 만들면 null이어야 한다")
    fun creationOperatorNotDefined() {
        // when
        val operator = Operator.of("&")

        // then
        assertThat(operator).isEqualTo(null)
    }

    @Test
    @DisplayName("Plus 연산자는 + 기호를 가지며 더하기 연산을 수행해야 한다")
    fun operatorPlus() {
        // when
        val operator = Operator.Plus

        // then
        assertThat(operator.sign).isEqualTo("+")
        assertThat(operator.operation(3, 5)).isEqualTo(8)
    }

    @Test
    @DisplayName("Minus 연산자는 - 기호를 가지며 빼기 연산을 수행해야 한다")
    fun operatorMinus() {
        // when
        val operator = Operator.Minus

        // then
        assertThat(operator.sign).isEqualTo("-")
        assertThat(operator.operation(8, 2)).isEqualTo(6)
    }

    @Test
    @DisplayName("Multiply 연산자는 * 기호를 가지며 곱하기 연산을 수행해야 한다")
    fun operatorMultiply() {
        // when
        val operator = Operator.Multiply

        // then
        assertThat(operator.sign).isEqualTo("*")
        assertThat(operator.operation(3, 5)).isEqualTo(15)
    }

    @Test
    @DisplayName("Divide 연산자는 / 기호를 가지며 나누기 연산을 수행해야 한다")
    fun operatorDivide() {
        // when
        val operator = Operator.Divide

        // then
        assertThat(operator.sign).isEqualTo("/")
        assertThat(operator.operation(8, 2)).isEqualTo(4)
    }
}