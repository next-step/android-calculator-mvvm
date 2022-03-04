package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.dodobest.data.ResultRecordEntity
import com.github.dodobest.domain.CalculatorRepository
import com.google.common.truth.Truth.assertThat
import com.github.dodobest.domain.Calculator
import com.github.dodobest.domain.Expression
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: CalculatorViewModel
    lateinit var expression: Expression
    lateinit var calculator: Calculator
    lateinit var calculatorRepository: CalculatorRepository

    @Before
    fun setUp() {
        expression = Expression()
        calculator = Calculator()
        calculatorRepository = mockk()
        viewModel = CalculatorViewModel(expression, calculator, calculatorRepository)
    }

    @Test
    fun `입력된 피연사자가 없을때, 피연산자 2 버튼을 누르면 화면에 2를 보여준다`() {
        // when : 피연산자 2 버튼을 누르면
        viewModel.addToExpression(2)

        // then : 화면에 2를 보여준다
        val actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("2")
    }

    @Test
    fun `이전에 8 버튼을 눌렀을 때, 5 버튼을 누르면 화면에 85를 보여준다`() {
        // given : 8 버튼을 눌렀을 때
        viewModel.addToExpression(8)

        // when : 5 버튼을 누르면
        viewModel.addToExpression(5)

        // then : 화면에 85를 보여준다
        val actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("85")
    }

    @Test
    fun `입력된 피연산자가 없을때, 연산자 버튼을 누르면 화면에 아무런 변화가 없다`() {
        // given : 입력된 피연산자가 없을때
        
        // when : 연산자 버튼을 누르면
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Plus)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Minus)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Divide)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Multiply)

        // then : 화면에 아무런 변화가 없다
        val actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("")
    }
    
    @Test
    fun `입력된 피연산자가 있을때, 연산자 버튼을 누르면 해당 기호를 화면에 보여준다`() {
        // given : 입력된 피연산자가 있을때
        viewModel.addToExpression(8)

        // when : 연산자 버튼을 누르면
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Plus)

        // then : 해당 기호를 화면에 보여준다
        val actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("8 +")
    }
    @Test
    fun `입력된 수식이 없을 때, 지우기 버튼을 누르면 화면에 아무런 변화가 없다`() {
        // given : 입력된 수식이 없을때

        // when : 지우기 버튼을 누르면
        viewModel.erase()
        viewModel.erase()

        // then : 화면에 아무런 변화가 없다
        val actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 없을 때, = 버튼을 누르면 화면에 아무런 변화가 없다`() {
        // given : 입력된 수식이 없을때

        // when : = 버튼을 누르면
        viewModel.equals()
        viewModel.equals()

        // then : 화면에 아무런 변화가 없다
        val actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("")
    }

    @Test
    fun `이전에 3+2= 버튼을 눌러 값 5를 얻었을 때, x 5를 누르면 화면에 25를 보여준다`() {
        // given : 이전에 3+2= 버튼을 눌러 값 5를 얻었을 때
        viewModel.addToExpression(3)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Plus)
        viewModel.addToExpression(2)
        viewModel.equals()

        var actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("5")

        // when : * 5를 누르면
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Multiply)
        viewModel.addToExpression(5)
        viewModel.equals()

        // then : 화면에 25를 보여준다
        actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("25")
    }



    @Test
    fun `입력된 수식이 있을 때, 지우기 버튼을 누르면 수식에 마지막으로 입력된 것이 지워져야 한다`() {
        // given : 입력된 수식이 있을 때
        viewModel.addToExpression(8)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Plus)
        viewModel.addToExpression(8)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Divide)

        // when : 지우기 버튼을 누르면
        viewModel.erase()

        // then : 수식에 마지막으로 입력된 것이 지워져야 한다
        var actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("8 + 8")

        // when : 지우기 버튼을 누르면
        viewModel.erase()

        // then : 수식에 마지막으로 입력된 것이 지워져야 한다
        actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("8 +")

        // when : 지우기 버튼을 누르면
        viewModel.erase()

        // then : 수식에 마지막으로 입력된 것이 지워져야 한다
        actual = viewModel.statement.value
        assertThat(actual.toString()).isEqualTo("8")
    }

    @Test
    fun `입력된 수식이 완전할 때, = 버튼을 누르면 입력된 수식의 결과를 화면에 보여준다`() {
        // given : 입력된 수식이 완전할 때
        viewModel.addToExpression(5)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Plus)
        viewModel.addToExpression(5)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Divide)
        viewModel.addToExpression(2)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Multiply)
        viewModel.addToExpression(3)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Minus)
        viewModel.addToExpression(2)

        // when : = 버튼을 누르면
        viewModel.equals()

        // then : 입력된 수식의 결과를 화면에 보여준다
        val actual = viewModel.statement.value
        assertThat(actual).isEqualTo("13")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, = 버튼을 누르면 완성되지 않은 수식입니다 토스트 메세지를 화면에 보여준다`() {
        // given : 입력된 수식이 완전하지 않을 때
        viewModel.addToExpression(5)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Plus)
        viewModel.addToExpression(5)
        viewModel.addToExpression(com.github.dodobest.domain.Operator.Divide)

        // when : = 버튼을 누르면
        viewModel.equals()

        // then : 완성되지 않은 수식입니다 토스트 메세지를 화면에 보여준다
        val actual = viewModel.showErrorMessage
        assertThat(actual).isNotEqualTo(null)
    }
}