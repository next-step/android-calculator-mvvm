package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.data.CalculatorDatabase
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExperimentalCoroutinesApi
@ExtendWith(value = [InstantExecutorExtension::class])
class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var db: CalculatorDatabase

    val dispatcher = StandardTestDispatcher()
    val scope = TestScope(dispatcher)

    @AfterEach
    fun destroy() {
        Dispatchers.resetMain()
    }

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        db = mockk()
        viewModel = CalculatorViewModel(calculatorDatabase = db)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        viewModel.addToExpression(1)

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("1")
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(1)), calculatorDatabase = db)

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("1 +")
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(1)), calculatorDatabase = db)

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("")
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() = scope.runTest {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(1, Operator.Plus, 2)), calculatorDatabase = db)

        // when
        viewModel.calculate()

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("3")
    }

    @ParameterizedTest
    @ValueSource(strings = ["+", "-", "*", "/"])
    fun `입력된 피연산자가 없을 때, 사용자가 연산자 버튼을 누르면 화면에 아무런 변화가 없어야 한다`(sign: String) {
        val operator = Operator.of(sign)
        checkNotNull(operator)
        viewModel.addToExpression(operator)

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.calculatorText.getOrAwaitValue()).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 완성되지 않은 수식입니다 토스트 메세지가 화면에 보여야 한다`() = scope.runTest {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(1, Operator.Plus)), calculatorDatabase = db)

        // when
        viewModel.calculate()

        // then
        assertThat(viewModel.showIncompleteExpressionError.getOrAwaitValue()).isEqualTo(Unit)
    }

    @Test
    fun `계산을 진행하면 수식과 결과의 기록이 남는다`() = scope.runTest {
        /* TODO: 히스토리 저장하는 것을 어떻게 테스트 해야할까..? */
        // given
        viewModel = CalculatorViewModel(Expression(listOf(1, Operator.Plus, 4)), calculatorDatabase = db)

        // when
        viewModel.calculate()

        // then
        assertThat(viewModel.calculateHistories.getOrAwaitValue()[0].expression.toString()).isEqualTo("1 + 4")
        assertThat(viewModel.calculateHistories.getOrAwaitValue()[0].result).isEqualTo(5)
    }
}
