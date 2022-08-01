package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class CalculatorViewModelTest {

    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var calculator: Calculator

    @BeforeEach
    internal fun setUp() {
        calculator = Calculator()
        calculatorViewModel = CalculatorViewModel(calculator)
    }

    @Test
    internal fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addToExpression(5)

        // then
        val actual = calculatorViewModel.expressionLiveData.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo("5")
    }

    @Test
    internal fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(5)

        // when
        calculatorViewModel.addToExpression(Operator.Plus)

        // then
        val actual = calculatorViewModel.expressionLiveData.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo("5 +")
    }

    @Test
    internal fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(5)
        calculatorViewModel.addToExpression(Operator.Plus)

        // when
        calculatorViewModel.removeLast()

        // then
        val actual = calculatorViewModel.expressionLiveData.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo("5")
    }

    @Test
    internal fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(5)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(5)

        // when
        calculatorViewModel.calculate()

        // then
        val actual = calculatorViewModel.expressionLiveData.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo("10")
    }

}