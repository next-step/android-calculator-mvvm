import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.CalculatorViewModel
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    private val viewModel = CalculatorViewModel()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `입력된 값이 없을때 1를 입력하면 1 이다`() {
        // given :

        // when :
        viewModel.addToExpression(1)

        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("1")
    }

    @Test
    fun `입력된 값이 1일때 1를 입력하면 1 이다`() {
        // given :
        viewModel.addToExpression(1)

        // when :
        viewModel.addToExpression(1)

        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("11")
    }

    @Test
    fun `입력된 값이 1일때 +를 입력하면 수식이 1 +이다`() {
        // given :
        viewModel.addToExpression(1)

        // when :
        viewModel.addToExpression(Operator.Plus)

        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("1 +")
    }

    @Test
    fun `입력된 값이 1 +일때 +를 입력하면 수식이 1 +이다`() {
        // given :
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        // when :
        viewModel.addToExpression(Operator.Plus)

        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("1 +")
    }

    @Test
    fun `입력된 값이 1 + 일때 뒤로가기를 입력하면 수식이 1이다`() {
        // given :
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        // when :
        viewModel.removeLast()

        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("1")
    }

    @Test
    fun `입력된 값이 1 + 2일때 =을 입력하면 수식이 3이다`() {
        // given :
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)

        // when :
        viewModel.calculate()

        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("3")
    }

    @Test
    fun `입력된 값이 1 + 일때 =을 입력하면 수식이 1 +이다`() {
        // given :
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        // when :
        viewModel.calculate()

        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("1 +")
    }
}
