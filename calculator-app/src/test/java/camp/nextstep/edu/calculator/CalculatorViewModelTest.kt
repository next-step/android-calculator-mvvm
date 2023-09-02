package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Operator
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `뷰모델이 초기화됐을 때 수식 기본값은 비어 있다`() {
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = ""
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 피연산자가 없을 때 피연산자를 추가하면 수식에 추가된다`() {
        viewModel.addToExpression(5)
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "5"
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 피연산자가 있을 때 피연산자를 추가하면 연속된 숫자가 수식에 추가된다`() {
        viewModel.addToExpression(5)
        viewModel.addToExpression(6)
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "56"
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 피연산자가 없을 때 연산자를 추가하면 수식에 추가되지 않는다`() {
        viewModel.addToExpression(Operator.Plus)
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = ""
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 피연산자가 있을 때 연산자를 추가하면 수식에 추가된다`() {
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Plus)
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "5 +"
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `수식의 마지막이 연산자일 때 연산자를 추가하면 연산자가 변경된다`() {
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(Operator.Minus)
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "5 -"
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 없을 때 수식을 지우면 아무 변화가 일어나지 않는다`() {
        viewModel.removeLast()
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = ""
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 있을 때 지우기 버튼을 클릭하면 수식의 마지막이 지워진다`() {
        viewModel.addToExpression(32)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)
        viewModel.removeLast()
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "32 +"
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 완전할 때 연산 버튼을 클릭하면 계산 결과가 도출된다`() {
        viewModel.addToExpression(32)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)
        viewModel.calculate()
        val expression = viewModel.expression.getOrAwaitValue().toString()
        val expected = "33"
        assertThat(expression).isEqualTo(expected)
    }
}