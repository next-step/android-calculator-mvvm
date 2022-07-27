package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CalculatorViewModelTest {

    @JvmField
    @RegisterExtension
    val instantTaskExecutorExtension = InstantTaskExecutorExtension()

    private val viewModel = CalculatorViewModel()

    @Test
    fun `입력된 피연산자가 없을 때, 숫자가 입력되면 숫자가 추가된다`() {
        // when
        viewModel.addOperand(3)

        // then
        val actual = viewModel.display.getOrAwaitValue()
        assertThat(actual).isEqualTo("3")
    }

    @Test
    fun `입력된 피연산자가 있을 때, 기존 숫자 뒤에 추가한 숫자가 보여야한다`() {
        // given 1
        viewModel.addOperand(1)

        // when
        viewModel.addOperand(3)

        // then
        val actual = viewModel.display.getOrAwaitValue()
        assertThat(actual).isEqualTo("13")
    }

    @Test
    fun `입력된 피연산자가 있을 때, 연산자를 입력하면 연산자가 추가된다`() {
        // given
        viewModel.addOperand(7)

        // when
        viewModel.addOperator(Operator.Plus)

        // then
        val actual = viewModel.display.getOrAwaitValue()
        assertThat(actual).isEqualTo("7 +")
    }

    @ParameterizedTest
    @EnumSource(Operator::class)
    fun `입력된 피연산자가 없을 때, 연산자 추가를 시도해도 아무일도 안일어난다`(operator: Operator) {
        // when
        viewModel.addOperator(operator)

        // then
        val actual = viewModel.display.getOrAwaitValue()
        assertThat(actual).isEmpty()
    }

    @Test
    fun `마지막 수식이 연산자일 때, 연산자 추가를 시도하면 마지막 연산자를 덮어쓴다`() {
        // given "1 +"
        viewModel.addOperand(1)
        viewModel.addOperator(Operator.Plus)

        // when
        viewModel.addOperator(Operator.Minus)

        // then
        val actual = viewModel.display.getOrAwaitValue()
        assertThat(actual).isEqualTo("1 -")
    }

    @Test
    fun `입력된 수식이 없을 때, 지우기 시도를 해도 아무일도 일어나지 않는다`() {
        // when
        viewModel.removeLast()

        // then
        val actual = viewModel.display.getOrAwaitValue()
        assertThat(actual).isEmpty()
    }

    @Test
    fun `입력된 수식이 있을 때, 지우기 시도를 하면, 마지막으로 입력된 연산자, 피연산자가 지워져야 한다`() {
        // given "7 +"
        viewModel.addOperand(7)
        viewModel.addOperator(Operator.Plus)

        // when
        viewModel.removeLast()

        // then
        val actual = viewModel.display.getOrAwaitValue()
        assertThat(actual).isEqualTo("7")
    }

    @Test
    fun `입력된 수식이 완전할 때, 계산 시도하면 수식이 계산된다`() {
        // given
        viewModel.addOperand(7)
        viewModel.addOperator(Operator.Multiply)
        viewModel.addOperand(7)

        // when
        viewModel.calculate()

        // then
        val actual = viewModel.display.getOrAwaitValue()
        assertThat(actual).isEqualTo("49")
    }

    @Test
    fun `입력된 수식이 불완전할 때, 계산을 시도하면 불완전한 수식 이벤트가 발생한다`() {
        // given
        viewModel.addOperand(7)
        viewModel.addOperator(Operator.Multiply)

        // when
        viewModel.calculate()

        // then
        val actual = viewModel.event.getOrAwaitValue().consume()
        assertThat(actual).isInstanceOf(CalculatorViewModel.ViewEvent.IncompleteExpressionError::class.java)
    }

}