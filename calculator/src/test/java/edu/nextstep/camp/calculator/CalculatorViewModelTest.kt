package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
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
    fun `입력된_피연사자가 없을때 사용자가 피연산자 0 버튼을 누르면 화면에 해당 숫자가 보여야 한다`() {
        //when
        viewModel.addOperandToExpression(0)
        val actual = viewModel.result.value.toString()
        //then
        assertThat(actual).isEqualTo("0")
    }

    @Test
    fun `입력된 피연산자 8이 입력되어 있을 때 9를 입력하면 89가 보여야 한다`() {
        //given
        viewModel.addOperandToExpression(8)

        //when
        viewModel.addOperandToExpression(9)
        val actual = viewModel.result.value.toString()

        //then
        assertThat(actual).isEqualTo("89")
    }

    @Test
    fun `입력된 피연산자가 없을 때 사용자가 연산자 +를 누르면 화면에 아무런 변화가 없어야 한다`() {
        //when
        viewModel.addOperatorToExpression(Operator.Plus)
        val actual = viewModel.result.value.toString()

        //then
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `1에서 연산자 +를 클릭하고 사용자가 연산자 -를 누르면 해당 기호로 변해야 한다`() {
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
    fun `입력된 수식이 없을때, 사용자가 지우기 버튼을 누르면 아무런 변화가 없어야 한다`() {
        //when
        viewModel.deleteExpression()
        val actual = viewModel.result.value.toString()

        //then
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `32 + 1 이라는 수식이 있을 때, 사용자가 지우기 버튼을 2번 연속 누르면 마지막으로 입력된 피연산자 1과 연산자 +가 지워져야 한다`() {
        //given
        val expression = Expression(listOf(32,Operator.Plus,1))
        viewModel.expression = expression

        //when
        viewModel.deleteExpression()
        viewModel.deleteExpression()
        val actual = viewModel.result.value.toString()

        //then
        assertThat(actual).isEqualTo("32")
    }

    @Test
    fun `3 + 2 라는 완전한 수식이 있을 때 = 버튼을 누르면 계산 결과가 도출된다`() {
        //given
        val expression = Expression(listOf(3, Operator.Plus, 2))
        viewModel.expression = expression

        //when
        viewModel.calculateExpression()
        val actual = viewModel.result.value.toString()

        //then
        assertThat(actual).isEqualTo("5")
    }

    @Test
    fun `3 + 라는 불완전한 수식일 때, =을 누르면 완성되지 않은 수식 error을 발생한다`() {
        //given
        viewModel.addOperandToExpression(3)
        viewModel.addOperatorToExpression(Operator.Plus)

        //when
        viewModel.calculateExpression()
        val actual = viewModel.error.value

        //then
        assertThat(actual).isEqualTo(CalculatorErrorEvent.IncompleteExpressionError)
    }
}