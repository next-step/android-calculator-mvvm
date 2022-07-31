package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operand
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
        // given
        val operand = Operand(10)

        // when
        calculatorViewModel.addElement(operand)

        // then
        assertThat(calculatorViewModel.state.getOrAwaitValue()).isEqualTo(
            StringExpressionState.of("10")
        )
    }
}
