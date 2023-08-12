package camp.nextstep.edu.calculator.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.repo.HistoryRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var repository: HistoryRepository

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
    }

    @Test
    fun `입력된 피연산자가 없을 때 1클릭하면 1이 보여야 함`() {
        viewModel = CalculatorViewModel(Expression(listOf(1)), repository)
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `5 + 1 클릭하면 5 + 1이 보여야함`() {
        viewModel = CalculatorViewModel(Expression(listOf(5, Operator.Plus, 1)), repository)
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("5 + 1")
    }

    @Test
    fun `입력된 피연산자가 없을 때, +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        viewModel = CalculatorViewModel(Expression(listOf()), repository)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(Operator.Minus)
        viewModel.addToExpression(Operator.Multiply)
        viewModel.addToExpression(Operator.Divide)
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `입력된 피연산자가 있을 때, +를 누르면 해당 화면이 보여야함  1 +은 1+1이 보여야함`() {
        viewModel = CalculatorViewModel(Expression(listOf(1, Operator.Plus)), repository)
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("1 +")
    }

    @Test
    fun `식이 없을 때 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        viewModel = CalculatorViewModel(repository = repository)
        viewModel.removeLast()
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        viewModel = CalculatorViewModel(Expression(listOf(32, Operator.Plus, 1)), repository)
        var actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("32 + 1")
        viewModel.removeLast()
        actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("32 +")
        viewModel.removeLast()
        actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("32")
        viewModel.removeLast()
        actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("3")
        viewModel.removeLast()
        actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `입력된 수신이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다 3 + 2 = 5`() {
        viewModel = CalculatorViewModel(Expression(listOf(3, Operator.Plus, 2)), repository)
        viewModel.calculate()
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("5")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 완성되지 않은 수식입니다 에러발생`() {
        viewModel = CalculatorViewModel(Expression(listOf(3, Operator.Plus)), repository)

        viewModel.calculate()
        val actual = viewModel.uiState.value
        assertThat(actual).isInstanceOf(CalculatorUiState.ErrorNotCompleteExpression::class.java)
    }
}
