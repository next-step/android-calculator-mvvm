package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(InstantExecutorExtension::class)
internal class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel

    @BeforeEach
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @ValueSource(
        strings = [
            "1", "2", "3", "4", "5", "6", "7", "8", "9",
        ]
    )
    @ParameterizedTest(name = "입력된 피연산자가 없을 때, 사용자가 피연산자 {0} 버튼을 누르면 화면에 {0} 보여야 한다.")
    fun test1(rawOperand: Int) {
        // when
        viewModel.addToExpression(rawOperand)

        // then
        val actual = viewModel.expressionResult.getOrAwaitValue()
        assertThat(actual).isEqualTo(rawOperand.toString())
    }

    @CsvSource(
        value = [
            "8,9,89",
            "80,90,8090",
        ]
    )
    @ParameterizedTest(name = "{0} 입력되어 있을 때 {1} 입력하면 {2} 보여야 한다.")
    fun test2(rawOperand1: Int, rawOperand2: Int, expected: String) {
        // given
        viewModel.addToExpression(rawOperand1)

        // when
        viewModel.addToExpression(rawOperand2)

        // then
        val actual = viewModel.expressionResult.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @CsvSource(
        value = [
            "8,9,89",
            "80,90,8090",
        ]
    )
    @ParameterizedTest(name = "입력된 피연산자가 없을 때, 사용자가 연산자 {0} 버튼을 누르면 화면에 아무런 변화가 없어야 한다.")
    fun test3(rawOperator: String) {
        // given
        val operator = Operator.of(rawOperator) ?: return

        // when
        viewModel.addToExpression(operator)

        // then
        val actual = viewModel.expressionResult.getOrAwaitValue()
        assertThat(actual).isEqualTo(Expression.EMPTY.toString())
    }

    @CsvSource(
        value = [
            "1,+,1 +",
            "10,-,10 -",
        ]
    )
    @ParameterizedTest(name = "입력된 피연산자 {0} 있을 때, 사용자가 연산자 {1} 버튼을 누르면 화면에 {2} 보여야 한다.")
    fun test4(rawOperand: Int, rawOperator: String, expected: String) {
        // given
        viewModel.addToExpression(rawOperand)

        // when
        val operator = Operator.of(rawOperator) ?: return
        viewModel.addToExpression(operator)

        // then
        val actual = viewModel.expressionResult.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @DisplayName("입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다")
    fun test5() {
        // when
        viewModel.removeLast()

        // then
        val actual = viewModel.expressionResult.getOrAwaitValue()
        assertThat(actual).isEqualTo(Expression.EMPTY.toString())
    }

    @CsvSource(
        value = [
            "1,+,1",
            "10,-,10",
        ]
    )
    @ParameterizedTest(name = "입력된 수식 {0} {1} 있을 때, 사용자가 지우기 버튼을 누르면 {2} 보여야 한다.")
    fun test6(rawOperand: Int, rawOperator: String, expected: String) {
        // given
        val operator = Operator.of(rawOperator) ?: return
        viewModel.addToExpression(rawOperand)
        viewModel.addToExpression(operator)

        // when
        viewModel.removeLast()

        // then
        val actual = viewModel.expressionResult.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @CsvSource(
        value = [
            "1,+,1,2",
            "10,-,10,0",
            "5,*,5,25",
            "10,/,2,5",
        ]
    )
    @ParameterizedTest(name = "완전한 수식 {0} {1} {2} 있을 때, = 버튼을 누르면 {2} 보여야 한다.")
    fun test7(rawOperand1: Int, rawOperator: String, rawOperand2: Int, expected: String) {
        // given
        val operator = Operator.of(rawOperator) ?: return
        viewModel.addToExpression(rawOperand1)
        viewModel.addToExpression(operator)
        viewModel.addToExpression(rawOperand2)

        // when
        viewModel.calculate()

        // then
        val actual = viewModel.expressionResult.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @DisplayName("입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 `완성되지 않은 수식입니다` 토스트 메세지가 화면에 보여야 한다.")
    fun test8() {
        // given
        viewModel.addToExpression(10)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.calculate()

        // then
        val actual = viewModel.eventShowIncompleteExpressionError.getOrAwaitValue()
        assertThat(actual).isEqualTo(null)
    }
}
