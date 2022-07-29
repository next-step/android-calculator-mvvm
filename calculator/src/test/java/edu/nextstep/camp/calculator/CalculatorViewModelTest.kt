package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by link.js on 2022. 07. 29..
 */
@RunWith(JUnit4::class)
class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
        viewModel.expression.observeForever {}
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        viewModel.addToExpression(2)
        assertThat(viewModel.expression.value.toString()).isEqualTo("2")
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)

        assertThat(viewModel.expression.value.toString()).isEqualTo("2 +")
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.removeLast()

        assertThat(viewModel.expression.value.toString()).isEqualTo("2")
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)

        viewModel.calculate()

        assertThat(viewModel.expression.value.toString()).isEqualTo("4")
    }

    @Test
    fun `계산식이 정확하지 않으면 토스트를 보낸다`() {
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.calculate()

        assertThat(viewModel.incompleteExpressionErrorEvent.value).isEqualTo(CalculatorViewModel.ExpressionError.ERROR)
    }
}