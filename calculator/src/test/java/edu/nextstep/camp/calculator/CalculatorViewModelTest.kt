package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringExpressionState
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class CalculatorViewModelTest {

    @JvmField
    @RegisterExtension
    val instantTaskExecutorExtension = InstantTaskExecutorExtension()

    private val calculatorViewModel = CalculatorViewModel()

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addElement(Operand(10))

        // then
        assertThat(calculatorViewModel.state.getOrAwaitValue()).isEqualTo(
            StringExpressionState.of("10")
        )
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addElement(Operand(1))
        calculatorViewModel.addElement(Operator.PLUS)

        // then
        assertThat(calculatorViewModel.state.getOrAwaitValue()).isEqualTo(
            StringExpressionState.of("1 +")
        )
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addElement(Operand(1))
        calculatorViewModel.removeElement()

        // then
        assertThat(calculatorViewModel.state.getOrAwaitValue()).isEqualTo(
            StringExpressionState.EmptyState()
        )
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // when
        calculatorViewModel.addElement(Operand(1))
        calculatorViewModel.addElement(Operator.PLUS)
        calculatorViewModel.addElement(Operand(2))
        calculatorViewModel.calculate()

        // then
        assertThat(calculatorViewModel.calculationResult.getOrAwaitValue()).isEqualTo(
            Operand(3)
        )
    }
}
