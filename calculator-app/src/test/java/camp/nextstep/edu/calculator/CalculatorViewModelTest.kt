package camp.nextstep.edu.calculator

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import camp.nextstep.edu.calculator.data.local.CalculatorDatabase
import camp.nextstep.edu.calculator.data.repository.CalculatorRepositoryImpl
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CalculatorViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var repository: CalculatorRepository

    @Before
    fun init() {
        val context: Context = ApplicationProvider.getApplicationContext()
        repository =
            CalculatorRepositoryImpl(CalculatorDatabase.getDatabase(context).calculatorDao())
        viewModel = CalculatorViewModel(repository)
    }

    @Test
    fun `입력된 피연산자가 없을 때, 사용자가 피연산자 버튼을 누르면 해당 숫자가 추가된다`() {
        // when
        viewModel.addToExpression(1)

        // then
        assertThat(viewModel.uiState.getOrAwaitValue().result).isEqualTo("1")
    }

    @Test
    fun `입력된 피연산자가 있을 때, 사용자가 피연산자 버튼을 누르면 해당 숫자가 추가된다2`() {
        // given
        viewModel = CalculatorViewModel(repository, Expression(listOf(5, Operator.Plus)))

        // when
        viewModel.addToExpression(3)

        // then
        assertThat(viewModel.uiState.getOrAwaitValue().result).isEqualTo("5 + 3")
    }

    @Test
    fun `입력된 피연산자가 있을 때, 기존 숫자 뒤에 해당 숫자가 화면에 보여야 한다`() {
        // given
        viewModel = CalculatorViewModel(repository, Expression(listOf(8)))

        // when
        viewModel.addToExpression(9)

        // then
        assertThat(viewModel.uiState.getOrAwaitValue().result).isEqualTo("89")
    }

    @Test
    fun `입력된 피연산자가 없을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        // when
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(Operator.Minus)
        viewModel.addToExpression(Operator.Multiply)
        viewModel.addToExpression(Operator.Divide)

        // then
        assertThat(viewModel.uiState.getOrAwaitValue().result).isEmpty()
    }

    @Test
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다`() {
        // given
        viewModel = CalculatorViewModel(repository, Expression(listOf(8)))

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        assertThat(viewModel.uiState.getOrAwaitValue().result).isEqualTo("8 +")
    }

    @Test
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다2`() {
        // given
        viewModel = CalculatorViewModel(repository, Expression(listOf(8, Operator.Plus)))

        // when
        viewModel.addToExpression(Operator.Minus)

        // then
        assertThat(viewModel.uiState.getOrAwaitValue().result).isEqualTo("8 -")
    }

    @Test
    fun `입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.uiState.getOrAwaitValue().result).isEmpty()
    }

    @Test
    fun `입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        // given
        viewModel = CalculatorViewModel(repository, Expression(listOf(32, Operator.Plus, 1)))

        // when
        viewModel.removeLast()
        viewModel.removeLast()

        // then
        assertThat(viewModel.uiState.getOrAwaitValue().result).isEqualTo("32")
    }

    @Test
    fun `입력된 수신이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다`() {
        // given
        viewModel = CalculatorViewModel(repository, Expression(listOf(3, Operator.Plus, 2)))

        // when
        viewModel.calculate()

        // then
        assertThat(viewModel.uiState.getOrAwaitValue().result).isEqualTo("5")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 완성되지 않은 수식입니다 토스트 메세지가 화면에 보여야 한다`() {
        // given
        viewModel = CalculatorViewModel(repository, Expression(listOf(3, Operator.Plus)))

        // when
        viewModel.calculate()

        // then
        assertThat(viewModel.uiEffect.getOrAwaitValue()).isEqualTo(UiEffect.InCompleteExpressionError)
    }
}
