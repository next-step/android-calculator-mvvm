package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.data.CalculatorDao
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var calculatorDao: CalculatorDao

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        calculatorDao = mockk(relaxed = true)
        viewModel = CalculatorViewModel(calculator = Calculator(), calculatorDao = calculatorDao)
    }

    @Test
    fun `숫자 1이 입력되면 1이 보여아 한다`() {
        // when
        viewModel.addToExpression(1)

        // then
        val expected = "1"
        assertThat(viewModel.expression.getOrAwaitValue().toString()).isEqualTo(expected)
    }

    @Test
    fun `수식 2 + 3이 입력되면 2 + 3이 보여아 한다`() {
        // when
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(3)

        // then
        val expected = "2 + 3"
        assertThat(viewModel.expression.getOrAwaitValue().toString()).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 2 + 3 일 때 지우기 버튼을 누르면 2 + 이 된다`() {
        // given
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(3)

        // when
        viewModel.removeLast()

        // then
        val expected = "2 +"
        assertThat(viewModel.expression.getOrAwaitValue().toString()).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 2 + 3 × 5 일 때 = 버튼을 누르면 25 이 보여야 한다`() {
        // given
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Multiply)
        viewModel.addToExpression(5)

        // when
        viewModel.calculate()

        // then
        val expected = "25"
        assertThat(viewModel.expression.getOrAwaitValue().toString()).isEqualTo(expected)
    }
}

