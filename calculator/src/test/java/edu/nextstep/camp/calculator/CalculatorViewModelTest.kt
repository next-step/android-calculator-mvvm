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
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when : 숫자 2 입력
        viewModel.addToExpression(2)

        // then : 숫자 2가 보인다.
        assertThat(viewModel.expression.value.toString()).isEqualTo("2")
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given : 숫자 2 입력
        viewModel.addToExpression(2)

        // when : 연산자 + 를 입력
        viewModel.addToExpression(Operator.Plus)

        // then : 2 + 가 보인다.
        assertThat(viewModel.expression.value.toString()).isEqualTo("2 +")
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given : 2 +
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)

        // when : 지우기를 누르면
        viewModel.removeLast()

        // then : 수식의 마지막인 + 가 지워지고 2가 보인다.
        assertThat(viewModel.expression.value.toString()).isEqualTo("2")
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given : 2 + 2
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)

        // when : 계산버튼을 누르면
        viewModel.calculate()

        // then : 4를 보여준다.
        assertThat(viewModel.expression.value.toString()).isEqualTo("4")
    }

    @Test
    fun `계산식이 정확하지 않을때 계산버튼을 누르면 토스트를 보낸다`() {
        // given : 정확하지 않은 계산식 입력
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)

        // when : 계산버튼을 누르면
        viewModel.calculate()

        // then : 토스트를 보낸다.
        val actual = viewModel.calculateErrorEvent.value?.consume()
        assertThat(actual).isInstanceOf(CalculatorViewModel.CalculateError.ExpressionError::class.java)
    }
}