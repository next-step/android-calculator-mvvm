package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = Calculator()
        viewModel = CalculatorViewModel()
        viewModel.expression.observeForever {  }
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        viewModel.addToExpression(1)

        // then
        val expected = Expression.EMPTY.plus(1)
        assertThat(viewModel.expression.value).isEqualTo(expected)
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)


        // then
        val expected = Expression.EMPTY.plus(1).plus(Operator.Plus)
        assertThat(viewModel.expression.value).isEqualTo(expected)
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // when
        viewModel.addToExpression(1)
        viewModel.removeLast()

        // then
        val expected = Expression.EMPTY.plus(1).removeLast()
        assertThat(viewModel.expression.value).isEqualTo(expected)
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
        val expected = calculator.calculate("3")?.let {
            Expression(listOf(it))
        }
        assertThat(viewModel.expression.value).isEqualTo(expected)
    }

    @Test
    fun `완성되지 않은 수식에 대해 계산이 실행되면 완성되지 않은 수식 에러가 발생한다`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.calculate()


        // then
        val expected = CalculatorViewModel.SideEffect.IncompleteExpressionError
        assertThat(viewModel.sideEffect.value?.consume()).isEqualTo(expected)
    }

    @Test
    fun `0으로 나누는 수식에 대해 계산이 실행되면 0으로 나누는 수식 에러가 발생한다`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Divide)
        viewModel.addToExpression(0)

        // when
        viewModel.calculate()


        // then
        val expected = CalculatorViewModel.SideEffect.DivideByZeroError
        assertThat(viewModel.sideEffect.value?.consume()).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 있을때 지우기를 누르면 마지막으로 입력된 연산자 혹은 피연산자가 지워진다`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Divide)
        viewModel.addToExpression(1)

        // when
        viewModel.removeLast()


        // then
        val expected = Expression.EMPTY.plus(1).plus(Operator.Divide).plus(1).removeLast()
        assertThat(viewModel.expression.value).isEqualTo(expected)
    }
}
