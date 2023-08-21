package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.ArithmeticOperator
import camp.nextstep.edu.calculator.domain.Expression
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var calculatorViewModel: CalculatorViewModel

    @Test
    fun `수식이_비었을_때_피연산자를_입력하면_피연산자가_추가된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression(""))
        // when
        val input = 7
        calculatorViewModel.addOperand(input)
        val expected = input.toString()
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `피연산자가_있을_때_피연산자를_입력하면_피연산자가_추가된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7"))
        // when
        val input = 7
        calculatorViewModel.addOperand(input)
        val expected = "7$input"
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `연산자가_있을_때_피연산자를_입력하면_피연산자가_추가된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + "))
        // when
        val input = 7
        calculatorViewModel.addOperand(input)
        val expected = "7 + $input"
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `수식이_비었을_때_연산자를_입력하면_연산자가_추가되지_않는다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression(""))
        // when
        val input = ArithmeticOperator.PLUS
        calculatorViewModel.addOperator(input)
        val expected = ""
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `피연산자가_있을_때_연산자를_입력하면_연산자가_추가된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7"))
        // when
        val input = ArithmeticOperator.PLUS
        calculatorViewModel.addOperator(input)
        val expected = "7 ${input.value} "
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `연산자가_있을_때_연산자를_입력하면_연산자가_변경된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + "))
        // when
        val input = ArithmeticOperator.MULTIPLY
        calculatorViewModel.addOperator(input)
        val expected = "7 ${input.value} "
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `정상적인_수식일_때_Equal을_입력하면_수식을_계산한_결과값이_추가된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + 78"))
        // when
        calculatorViewModel.calculate()
        val expected = "85"
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `비정상적인_수식일_때_Equal을_입력하면_이전_수식이_그대로_존재한다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + 78 - "))
        // when
        calculatorViewModel.calculate()
        val expected = "7 + 78 - "
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `비정상적인_수식일_때_Equal을_입력하면_경고_메시지_이벤트가_트리거된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + 78 - "))
        // when
        calculatorViewModel.calculate()
        // then
        val actual = calculatorViewModel.showWarningMessageEvent.getOrAwaitValue()
        assertThat(actual).isNotEmpty()
    }

    @Test
    fun `연산자가_마지막에_있을_때_삭제를_입력하면_수식의_마지막_연산자가_지워진다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + 78 - "))
        // when
        calculatorViewModel.delete()
        val expected = "7 + 78"
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `피연산자가_마지막에_있을_때_삭제를_입력하면_수식의_마지막_피연산자의_숫자가_지워진다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + 78"))
        // when
        calculatorViewModel.delete()
        val expected = "7 + 7"
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }
}
