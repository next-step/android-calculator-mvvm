package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.viewmodel.CalculatorViewModel
import edu.nextstep.camp.data.MemoryDao
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var memoryDao: MemoryDao
    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        memoryDao = mockk(relaxed = true)
        viewModel = CalculatorViewModel(memoryDao = memoryDao)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        // when
        viewModel.addToExpression(1)

        // then
        val expected = Expression(listOf(1))
        assertThat(viewModel.expressionEvent.getOrAwaitValue()).isEqualTo(expected)
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel.addToExpression(1)

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        val expected = Expression(listOf(1, Operator.Plus))
        assertThat(viewModel.expressionEvent.getOrAwaitValue()).isEqualTo(expected)
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel.addToExpression(1)

        // when
        viewModel.removeLast()

        // then
        val expected = Expression.EMPTY
        assertThat(viewModel.expressionEvent.getOrAwaitValue()).isEqualTo(expected)
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)

        // when
        viewModel.calculate()

        // then
        val expected = Expression(listOf(3))
        assertThat(viewModel.expressionEvent.getOrAwaitValue()).isEqualTo(expected)
    }
}