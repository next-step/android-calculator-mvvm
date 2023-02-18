package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Operator
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다`() {
        viewModel.addToExpression(1)

        assertEquals(viewModel.text.getOrAwaitValue(), "1")
    }

    @Test
    fun `입력된 피연산자가 있을 때, 기존 숫자 뒤에 해당 숫자가 화면에 보여야 한다 예를 들면, 8이 입력되어 있을 때 9를 입력하면 89가 보여야 한다`() {
        viewModel.addToExpression(8)
        viewModel.addToExpression(9)

        assertEquals(viewModel.text.getOrAwaitValue(), "89")
    }

    @Test
    fun `입력된 피연산자가 없을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        viewModel.addToExpression(Operator.Plus)

        assertEquals(viewModel.text.getOrAwaitValue(), "")
    }

    @Test
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다`() {
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        assertEquals(viewModel.text.getOrAwaitValue(), "1 +")
    }

    @Test
    fun `입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        viewModel.removeLast()

        assertEquals(viewModel.text.getOrAwaitValue(), "")
    }

    @Test
    fun `입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        viewModel.addToExpression(3)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)

        viewModel.removeLast()
        viewModel.removeLast()
        viewModel.removeLast()
        viewModel.removeLast()
        viewModel.removeLast()

        assertEquals(viewModel.text.getOrAwaitValue(), "")
    }

    @Test
    fun `입력된 수신이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다`() {
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)
        viewModel.calculate()

        assertEquals(viewModel.text.getOrAwaitValue(), "5")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 완성되지 않은 수식입니다 토스트 메세지가 화면에 보여야 한다`() {
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)
        viewModel.calculate()

        assertEquals(viewModel.warning.getOrAwaitValue(), Unit)
    }
}
