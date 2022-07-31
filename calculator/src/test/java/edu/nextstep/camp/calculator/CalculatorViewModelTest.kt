package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.counter.getOrAwaitValue
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

class CalculatorViewModelTest {

    @JvmField
    @RegisterExtension
    val instantTaskExecutorExtension = InstantTaskExecutorExtension()

    @ParameterizedTest(name = "입력 : {0}")
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9])
    internal fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`(value: Int) {
        //given
        val viewModel = CalculatorViewModel()
        val expected = Expression(listOf(value))

        //when
        viewModel.onViewEvent(CalculatorViewEvent.operand(value))
        val actual = viewModel.onViewState.getOrAwaitValue().consume() as CalculatorViewState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }

    @ParameterizedTest
    @EnumSource(value = Operator::class)
    internal fun `숫자 뒤에 연산자가 입력되면 연산자가 추가되고 변경된 수식을 보여줘야 한다`(value: Operator) {
        //given
        val viewModel = CalculatorViewModel(expression = Expression(listOf(1)))
        val expected = Expression(listOf(1, value))

        //when
        viewModel.onViewEvent(CalculatorViewEvent.AddOperator(value))
        val actual = viewModel.onViewState.getOrAwaitValue().consume() as CalculatorViewState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }

    @Test
    internal fun `숫자 뒤에 숫자가 입력되면 변경된 숫자를 보여줘야 한다`() {
        //given
        val viewModel = CalculatorViewModel(expression = Expression(listOf(123)))
        val expected = Expression(listOf(1239))

        //when
        viewModel.onViewEvent(CalculatorViewEvent.operand(9))
        val actual = viewModel.onViewState.getOrAwaitValue().consume() as CalculatorViewState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }


    @Test
    internal fun `연산자가 입력된 상태에서 연산자를 추가 입력하면 마지막으로 입력된 연산자로 변경된다`() {
        //given
        val viewModel = CalculatorViewModel(expression = Expression(listOf(1, Operator.Plus)))
        val expected = Expression(listOf(1, Operator.Multiply))

        //when
        viewModel.onViewEvent(CalculatorViewEvent.AddOperator(Operator.Minus))
        viewModel.onViewEvent(CalculatorViewEvent.AddOperator(Operator.Plus))
        viewModel.onViewEvent(CalculatorViewEvent.AddOperator(Operator.Divide))
        viewModel.onViewEvent(CalculatorViewEvent.AddOperator(Operator.Multiply))
        val actual = viewModel.onViewState.getOrAwaitValue().consume() as CalculatorViewState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }

    @Test
    internal fun `수식이 완성되지 않은 상태에서 계산을 하면 변화가 없다`() {
        //given
        val viewModel = CalculatorViewModel(
            expression = Expression(listOf(3, Operator.Multiply, 90, Operator.Minus))
        )

        val expected = CalculatorViewState.ShowIncompleteExpressionError

        //when
        viewModel.onViewEvent(CalculatorViewEvent.Calculate)
        val actual = viewModel.onViewState.getOrAwaitValue().consume() as CalculatorViewState.ShowIncompleteExpressionError

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `수식이 완성된 상태에서 계산을 하면 수식의 결과를 보여줘야 한다`() {
        //given
        val expected = 270
        val viewModel = CalculatorViewModel(
            expression = Expression(listOf(3, Operator.Multiply, 90))
        )

        //when
        viewModel.onViewEvent(CalculatorViewEvent.Calculate)
        val actual = viewModel.onViewState.getOrAwaitValue().consume() as CalculatorViewState.ShowResult

        //then
        assertThat(actual.result).isEqualTo(expected)
    }

    @Test
    internal fun `수식이 입력된 상태에서 삭제 버튼을 누르면 마지막 입력된 숫자 또는 연산자가 삭제된다`() {
        //given
        val expected = Expression(listOf(1, Operator.Plus, 3))
        val viewModel = CalculatorViewModel(
            expression = Expression(listOf(1, Operator.Plus, 32))
        )

        //when
        viewModel.onViewEvent(CalculatorViewEvent.RemoveLast)
        val actual = viewModel.onViewState.getOrAwaitValue().consume() as CalculatorViewState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }
}
