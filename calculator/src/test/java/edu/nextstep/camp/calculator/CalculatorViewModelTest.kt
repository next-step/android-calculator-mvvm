package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.repository.CalculateRepository
import edu.nextstep.camp.calculator.domain.repository.History
import edu.nextstep.camp.calculator.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import junitparams.naming.TestCaseName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import util.ExpressionUtil.toExpression
import util.HistoryUtil


@RunWith(JUnitParamsRunner::class)
class CalculatorViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var defaultRepository: CalculateRepository
    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        defaultRepository = mockk()
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    @Parameters(value = [
        "     | 1 | 1      ",
        "     | 2 | 2      ",
        "1 +  | 1 | 1 + 1  ",
        "12   | 1 | 121    "
    ])
    @TestCaseName("입력된 상태가 {0}일 때, 피연산자 {1} 버튼을 누르면 화면에 {2} 화면에 보여야 한다")
    fun `피연산자 입력 테스트`(initialString: String, operand: Int, expected: String) {
        val viewModel = CalculatorViewModel(calculator = Calculator(),
            initialExpression = initialString.toExpression(),
            defaultRepository
        )

        viewModel.addToExpression(operand = operand)

        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo(expected)
    }

    @Test
    @Parameters(value = [
        "1        |  +  |  1 +      ",
        "2        |  -  |  2 -      ",
        "1 + 1    |  /  |  1 + 1 /  ",
        "13       |  *  |  13 *     ",
        "13 +     |  *  |  13 *     ",
        "13 -     |  -  |  13 -     ",
        "1 + 1 -  |  /  |  1 + 1 /  ",
    ])
    @TestCaseName("입력된 상태가 {0}일 때, 연산자 {1} 버튼을 누르면 화면에 {2} 화면에 보여야 한다")
    fun `연산자 입력 테스트`(initialString: String, operator: String, expected: String) {
        val viewModel = CalculatorViewModel(
            calculator = Calculator(),
            initialExpression = initialString.toExpression(),
            defaultRepository
        )

        viewModel.addToExpression(Operator.of(operator)!!)

        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo(expected)
    }

    @Test
    @Parameters(value = [
        "+",
        "-",
        "/",
        "*"
    ])
    @TestCaseName("입력된 수식이 없을 때 연산자 {0} 입력되면 아무런 변화가 없어야한다")
    fun `입력된 수식이 없을 때 연산자가 입력되면 아무런 변화가 없어야한다`(operator: String) {
        val viewModel = CalculatorViewModel(
            calculator = Calculator(),
            calculatorRepository = defaultRepository
        )

        viewModel.addToExpression(Operator.of(operator)!!)

        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("")
    }


    @Test
    fun `입력된 수식이 없을 때 지우기가 실행되면 아무런 변화가 없어야한다`() {
        val viewModel = CalculatorViewModel(
            calculator = Calculator(),
            calculatorRepository = defaultRepository
        )

        viewModel.removeLast()

        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 있을 때 지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        val viewModel = CalculatorViewModel(
            calculator = Calculator(),
            initialExpression = Expression(listOf(1, Operator.Plus, 2)),
            calculatorRepository = defaultRepository
        )

        // when
        viewModel.removeLast()

        // then
        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("1 +")
    }


    @Test
    @Parameters(value = [
        "1 + 2            |   3",
        "123 - 20 - 3     |   100",
        "30 / 2           |   15",
        "15 / 5 * 3 + 1   |   10"
    ])
    @TestCaseName("정상적인 수식 {0}이 있을 때 계산 시 결과가 {1}이 된다")
    fun `정상적인 수식이 있을 때 계산 시 계산 테스트`(initialString: String, expected: String) {
        val viewModel = CalculatorViewModel(
            calculator = Calculator(),
            initialExpression = initialString.toExpression(),
            calculatorRepository = defaultRepository
        )

        viewModel.calculate()

        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo(expected)
    }

    @Test
    fun `비정상적인 수식이 있을 때 테스트`() {
        val viewModel = CalculatorViewModel(
            calculator = Calculator(),
            initialExpression = Expression(listOf(1, Operator.Plus)),
            defaultRepository
        )

        viewModel.calculate()

        val actual = viewModel.calculateFailed.getOrAwaitValue()
        val displayExpression = viewModel.expression.getOrAwaitValue()

        assertThat(actual).isEqualTo(Unit)
        assertThat(displayExpression.toString()).isEqualTo("1 +")
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `저장된_계산한_결과들이_정상적으로_조회된다`() {
        val savedHistory = listOf(
            HistoryUtil.historyOf("1 + 1 - 1 * 8", "8"),
            HistoryUtil.historyOf("5 - 1 * 12", "48"),
            HistoryUtil.historyOf("1 + 1 / 1 * 8", "16"),
        )
        val viewModel = CalculatorViewModel(
            calculator = Calculator(),
            calculatorRepository = defaultRepository,
            ioDispatcher = testDispatcher
        )
        coEvery { defaultRepository.getHistoryAll() } returns savedHistory

        viewModel.showCalculateHistory()
        val actual = viewModel.calculateHistory.getOrAwaitValue()

        assertThat(actual).containsExactlyElementsIn(savedHistory)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `계산을_수행할_때_계산식과_결과가_저장이_된다`() {
        val initialString = "1 + 2 + 312 - 10 * 2"
        val viewModel = CalculatorViewModel(
            calculator = Calculator(),
            initialExpression = initialString.toExpression(),
            calculatorRepository = defaultRepository,
            ioDispatcher = testDispatcher
        )
        val saveParamSlot = slot<History>()
        coEvery { defaultRepository.save(capture(saveParamSlot)) } returns mockk()

        viewModel.calculate()
        val actual = saveParamSlot.captured

        assertThat(actual.expression.toString()).isEqualTo(initialString)
        assertThat(actual.result.toString()).isEqualTo("610")
    }
}