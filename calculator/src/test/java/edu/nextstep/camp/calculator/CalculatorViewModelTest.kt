package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.memoryview.MemoryUIModel
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import org.junit.Rule

import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `0을 빈 수식에 추가하면 0이 나온다`() {
        //when
        viewModel.addOperandToExpression(0)
        val actual = viewModel.result.value.toString()
        //then
        assertThat(actual).isEqualTo("0")
    }

    @Test
    fun `수식에 8이 있을 때 9를 추가하면 89가 된다`() {
        //given
        viewModel.addOperandToExpression(8)

        //when
        viewModel.addOperandToExpression(9)
        val actual = viewModel.result.value.toString()

        //then
        assertThat(actual).isEqualTo("89")
    }

    @Test
    fun `수식이 없을 때 연산자 +를 수식에 추가하면 추가되지 않는다`() {
        //when
        viewModel.addOperatorToExpression(Operator.Plus)
        val actual = viewModel.result.value.toString()

        //then
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `1에서 연산자 +를 추가하고 연산자 -를 추가하면 해당 기호로 변경된다`() {
        //given
        viewModel.addOperandToExpression(1)
        viewModel.addOperatorToExpression(Operator.Plus)

        //when
        viewModel.addOperatorToExpression(Operator.Minus)
        val actual = viewModel.result.value.toString()

        //then
        assertThat(actual).isEqualTo("1 -")
    }

    @Test
    fun `빈 수식일 때, 수식의 마지막을 제거하려하면 아무런 변화가 없다`() {
        //when
        viewModel.deleteExpression()
        val actual = viewModel.result.value.toString()

        //then
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `32 + 1 이라는 수식이 있을 때, 수식의 마지막을 2번 연속 지우면 마지막으로 추가된 피연산자 1과 연산자 +가 제거됐어야 한다`() {
        //given
        val expression = Expression(listOf(32,Operator.Plus,1))
        val viewModelWithExpression = CalculatorViewModel(expression)

        //when
        viewModelWithExpression.deleteExpression()
        viewModelWithExpression.deleteExpression()
        val actual = viewModelWithExpression.result.value.toString()

        //then
        assertThat(actual).isEqualTo("32")
    }

    @Test
    fun `3 + 2 라는 완전한 수식이 있을 때, 계산하면 계산 결과가 도출된다`() {
        //given
        val expression = Expression(listOf(3, Operator.Plus, 2))
        val viewModelWithExpression = CalculatorViewModel(expression)

        //when
        viewModelWithExpression.calculateExpression()
        val actual = viewModelWithExpression.result.value.toString()

        //then
        assertThat(actual).isEqualTo("5")
    }

    @Test
    fun `3 + 라는 불완전한 수식일 때, 계산하려 하면 완성되지 않은 수식이라는 error을 발생한다`() {
        //given
        viewModel.addOperandToExpression(3)
        viewModel.addOperatorToExpression(Operator.Plus)

        //when
        viewModel.calculateExpression()
        val actual = viewModel.error.value

        //then
        assertThat(actual).isEqualTo(CalculatorErrorEvent.IncompleteExpressionError)
    }

    @Test
    fun `계산할 때 수식과 그 결과가 저장되고 계산 기록들이 온전하게 저장되어 있다`() {
        //given
        val expression = Expression(listOf(32,Operator.Plus,1))
        val viewModelWithExpression = CalculatorViewModel(expression)
        viewModelWithExpression.calculateExpression()

        //when
        viewModelWithExpression.showMemoryView()
        val actual = viewModelWithExpression.memoryLog.value

        //then
        assertThat(actual).isEqualTo(listOf(MemoryUIModel(0, "32 + 1", "33")))
    }
}