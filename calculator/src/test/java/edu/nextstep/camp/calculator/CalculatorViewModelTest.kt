package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(value = [InstantExecutorExtension::class])
class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel

    @BeforeEach
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        viewModel.addToExpression(1)

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("1")
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(1)))

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("1 +")
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(1)))

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("")
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(1, Operator.Plus, 2)))

        // when
        viewModel.calculate()

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("3")
    }

    @ParameterizedTest
    @ValueSource(strings = ["+", "-", "*", "/"])
    fun `입력된 피연산자가 없을 때, 사용자가 연산자 버튼을 누르면 화면에 아무런 변화가 없어야 한다`(sign: String) {
        val operator = Operator.of(sign)
        checkNotNull(operator)
        viewModel.addToExpression(operator)

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 완성되지 않은 수식입니다 토스트 메세지가 화면에 보여야 한다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(1, Operator.Plus)))

        // when
        viewModel.calculate()

        // then
        assertThat(viewModel.showIncompleteExpressionError.getOrAwaitValue()).isEqualTo(Unit)
    }
}
