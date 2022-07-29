package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CalculatorViewModel

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(expression = Expression.EMPTY)

        // when
        viewModel.addToExpression(1)

        // then
        assertShowing("1")
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(expression = Expression(listOf(1)))

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        assertShowing("1 +")
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(expression = Expression(listOf(1)))

        // when
        viewModel.removeLast()

        // then
        assertShowing("")
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(expression = Expression(listOf(1, Operator.Plus, 2)))

        // when
        viewModel.calculate()

        // then
        assertShowing("3")
    }

    private fun assertShowing(expected: String) {
        val actual = viewModel.showingExpression.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }
}
