package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: CalculatorViewModel
    lateinit var expression: Expression
    lateinit var calculator: Calculator

    @Before
    fun setUp() {
        expression = Expression()
        calculator = Calculator()
        viewModel = CalculatorViewModel(expression, calculator)
    }

    @Test
    fun `입력된 피연사자가 없을때, 피연산자 2 버튼을 누르면 화면에 2를 보여준다`() {
        // when : 피연산자 2 버튼을 누르면
        viewModel.addToExpression(2)

        // then : 화면에 2를 보여준다
        val actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("2")
    }

    @Test
    fun `이전에 8 버튼을 눌렀을 때, 5 버튼을 누르면 화면에 85를 보여준다`() {
        // given : 8 버튼을 눌렀을 때
        viewModel.addToExpression(8)

        // when : 5 버튼을 누르면
        viewModel.addToExpression(5)

        // then : 화면에 85를 보여준다
        val actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("85")
    }

    @Test
    fun `입력된 피연산자가 없을때, 연산자 버튼을 누르면 화면에 아무런 변화가 없다`() {
        // given : 입력된 피연산자가 없을때
        
        // when : 연산자 버튼을 누르면
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(Operator.Minus)
        viewModel.addToExpression(Operator.Divide)
        viewModel.addToExpression(Operator.Multiply)

        // then : 화면에 아무런 변화가 없다
        val actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("")
    }
    
    @Test
    fun `입력된 피연산자가 있을때, 연산자 버튼을 누르면 해당 기호를 화면에 보여준다`() {
        // given : 입력된 피연산자가 있을때
        viewModel.addToExpression(8)

        // when : 연산자 버튼을 누르면
        viewModel.addToExpression(Operator.Plus)

        // then : 해당 기호를 화면에 보여준다
        val actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("8 +")
    }
    @Test
    fun `입력된 수식이 없을 때, 지우기 버튼을 누르면 화면에 아무런 변화가 없다`() {
        // given : 입력된 수식이 없을때

        // when : 지우기 버튼을 누르면
        viewModel.erase()
        viewModel.erase()

        // then : 화면에 아무런 변화가 없다
        val actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 있을 때, 지우기 버튼을 누르면 수식에 마지막으로 입력된 것이 지워져야 한다`() {
        // given : 입력된 수식이 있을 때
        viewModel.addToExpression(8)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(8)
        viewModel.addToExpression(Operator.Divide)

        // when : 지우기 버튼을 누르면
        viewModel.erase()

        // then : 수식에 마지막으로 입력된 것이 지워져야 한다
        var actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("8 + 8")

        // when : 지우기 버튼을 누르면
        viewModel.erase()

        // then : 수식에 마지막으로 입력된 것이 지워져야 한다
        actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("8 +")

        // when : 지우기 버튼을 누르면
        viewModel.erase()

        // then : 수식에 마지막으로 입력된 것이 지워져야 한다
        actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("8")
    }

    @Test
    fun `입력된 수식이 완전할 때, = 버튼을 누르면 입력된 수식의 결과를 화면에 보여준다`() {
        // given : 입력된 수식이 완전할 때
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Divide)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Multiply)
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Minus)
        viewModel.addToExpression(2)

        // when : = 버튼을 누르면
        viewModel.equals()

        // then : 입력된 수식의 결과를 화면에 보여준다
        val actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("13")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, = 버튼을 누르면 완성되지 않은 수식입니다 토스트 메세지를 화면에 보여준다`() {
        // given : 입력된 수식이 완전하지 않을 때
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Divide)

        // when : = 버튼을 누르면
        viewModel.equals()

        // then : 완성되지 않은 수식입니다 토스트 메세지를 화면에 보여준다
        val actual = viewModel.showErrorMessage.value
        assertThat(actual).isNotEqualTo(null)
    }
}