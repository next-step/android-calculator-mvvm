package camp.nextstep.edu.calculator.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import camp.nextstep.edu.calculator.domain.Operator
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val viewModel = CalculatorViewModel()

    @Test
    fun `입력된 피연산자가 없을 때 1클릭하면 1이 보여야 함`() {
        viewModel.addToExpression(1)
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `5 + 1 클릭하면 5 + 1이 보여야함`() {
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("5 + 1")
    }

    @Test
    fun `입력된 피연산자가 없을 때, +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(Operator.Minus)
        viewModel.addToExpression(Operator.Multiply)
        viewModel.addToExpression(Operator.Divide)
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `입력된 피연산자가 있을 때, +를 누르면 해당 화면이 보여야함  1 +은 1+1이 보여야함`() {
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("1 +")
    }

    @Test
    fun `입력된 수식이 없을 때 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        viewModel.removeLast()
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        viewModel.addToExpression(3)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)
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
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)
        viewModel.calculate()
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("5")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 완성되지 않은 수식입니다 에러발생`() {
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)

        val observer = Observer<CalculatorUiState> {}
        try {
            viewModel.uiState.observeForever(observer)
            viewModel.calculate()

            val state = viewModel.uiState.value
            assertThat(state).isNotNull()
        } finally {
            viewModel.uiState.removeObserver(observer)
        }
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("3 +")
    }
}
