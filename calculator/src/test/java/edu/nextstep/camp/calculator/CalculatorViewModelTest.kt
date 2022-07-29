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
        val actual = viewModel.showingExpression.getOrAwaitValue()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(expression = Expression(listOf(1)))

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        val actual = viewModel.showingExpression.getOrAwaitValue()
        assertThat(actual).isEqualTo("1 +")
    }
}
