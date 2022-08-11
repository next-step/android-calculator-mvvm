package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.domain.calculator.Operator
import edu.nextstep.camp.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("NonAsciiCharacters")
class CalculatorViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var calculatorViewModel: CalculatorViewModel

    @Before
    fun setup() {
        calculatorViewModel = CalculatorViewModel()
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addToExpression(1)

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("1")
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("1 +")
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.removeLast()

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("")
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(2)

        // when
        calculatorViewModel.calculate()

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("3")
    }

    @Test
    fun `연산식이 완성되지 않은 상태에서 계산이 실행되면 완성되지 않은 수식이라는 에러 메시지를 화면에 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)

        // when
        calculatorViewModel.calculate()

        // then
        val actual = calculatorViewModel.errorMessage.getOrAwaitValue()
        assertThat(actual).isEqualTo(UiText.StringResource(R.string.incomplete_expression))
    }
}