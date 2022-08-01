package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.HistoryRepository
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK private lateinit var historyRepository: HistoryRepository
    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(
            expression = Expression.EMPTY,
            calculator = Calculator(historyRepository)
        )

        // when
        viewModel.addToExpression(1)

        // then
        assertShowing("1")
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(
            expression = Expression(listOf(1)),
            calculator = Calculator(historyRepository)
        )

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        assertShowing("1 +")
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(
            expression = Expression(listOf(1)),
            calculator = Calculator(historyRepository)
        )

        // when
        viewModel.removeLast()

        // then
        assertShowing("")
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        viewModel = CalculatorViewModel(
            expression = Expression(listOf(1, Operator.Plus, 2)),
            calculator = Calculator(historyRepository)
        )

        // when
        viewModel.calculate()

        // then
        assertShowing("3")
    }

    @Test
    fun `완성되지 않은 수식에 대한 계산이 실행되면 오류를 화면에 표시한다`() {
        // given
        viewModel = CalculatorViewModel(
            expression = Expression(listOf(1, Operator.Plus)),
            calculator = Calculator(historyRepository)
        )

        // when
        viewModel.calculate()

        // then
        val actual = viewModel.calculatorError.getOrAwaitValue()
        assertThat(actual).isEqualTo(CalculationException)
    }

    @Test
    fun `계산 결과가 존재할 때 기록 버튼을 누르면 화면에 기록을 표시한다`() {
        // given
        val historyList = listOf(History("8 - 3", 5))
        viewModel = CalculatorViewModel(
            calculator = Calculator(
                historyRepository = historyRepository,
                historyList = historyList
            )
        )

        // when
        viewModel.toggleHistory()

        // then
        val actualList = viewModel.history.getOrAwaitValue()
        val expectedList = listOf(HistoryItem("8 - 3", "= 5"))
        assertThat(actualList).isEqualTo(expectedList)

        val actualFlag = viewModel.isShowingHistory.getOrAwaitValue()
        assertThat(actualFlag).isEqualTo(true)
    }

    private fun assertShowing(expected: String) {
        val actual = viewModel.expression.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo(expected)
    }
}
