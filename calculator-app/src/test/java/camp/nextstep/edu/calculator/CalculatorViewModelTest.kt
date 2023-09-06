package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.data.CalculatorRepository
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: CalculatorRepository

    @Before
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun `뷰모델이 초기화됐을 때 수식 기본값은 비어 있다`() {
        val viewModel = CalculatorViewModel(
            Expression.EMPTY,
            repository
        )
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = ""
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 피연산자가 없을 때 피연산자를 추가하면 수식에 추가된다`() {
        val viewModel = CalculatorViewModel(
            Expression(listOf(5)),
            repository
        )
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "5"
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 피연산자가 있을 때 피연산자를 추가하면 연속된 숫자가 수식에 추가된다`() {
        val viewModel = CalculatorViewModel(
            Expression(listOf(5)),
            repository
        )
        viewModel.addToExpression(6)
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "56"
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 피연산자가 없을 때 연산자를 추가하면 수식에 추가되지 않는다`() {
        val viewModel = CalculatorViewModel(
            Expression.EMPTY,
            repository
        )
        viewModel.addToExpression(Operator.Plus)
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = ""
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 피연산자가 있을 때 연산자를 추가하면 수식에 추가된다`() {
        val viewModel = CalculatorViewModel(
            Expression(listOf(5, Operator.Plus)),
            repository
        )
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "5 +"
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `수식의 마지막이 연산자일 때 연산자를 추가하면 연산자가 변경된다`() {
        val viewModel = CalculatorViewModel(
            Expression(listOf(5, Operator.Plus)),
            repository
        )
        viewModel.addToExpression(Operator.Minus)
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "5 -"
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 없을 때 수식을 지우면 아무 변화가 일어나지 않는다`() {
        val viewModel = CalculatorViewModel(
            Expression.EMPTY,
            repository
        )
        viewModel.removeLast()
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = ""
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 있을 때 지우기 버튼을 클릭하면 수식의 마지막이 지워진다`() {
        val viewModel = CalculatorViewModel(
            Expression(listOf(32, Operator.Plus, 1)),
            repository
        )
        viewModel.removeLast()
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "32 +"
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 완전할 때 연산 버튼을 클릭하면 계산 결과가 도출된다`() {
        val viewModel = CalculatorViewModel(
            Expression(listOf(32, Operator.Plus, 1)),
            repository
        )
        viewModel.calculate()
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "33"
        assertThat(expression).isEqualTo(expected)
    }
}