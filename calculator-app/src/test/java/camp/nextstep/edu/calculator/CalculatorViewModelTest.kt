package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.calculator_data.Injector
import com.example.domain.models.*
import com.example.domain.usecases.GetHistoriesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var calculator: Calculator
    private lateinit var getHistoriesUseCase: GetHistoriesUseCase

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {

        val repository = Injector.provideRepository(
            context = ApplicationProvider.getApplicationContext(),
            isInMemory = true
        )

        calculator = Calculator(historyRepository = repository)
        getHistoriesUseCase = GetHistoriesUseCase(historyRepository = repository)
    }

    @Test
    fun `피연산자를 수식에 추가`() {
        // given
        viewModel =
            CalculatorViewModel(calculator = calculator, getHistoriesUseCase = getHistoriesUseCase)

        // when
        viewModel.addTerm(1)

        // then
        assertEquals(viewModel.text.getOrAwaitValue(), "1")
    }

    @Test
    fun `연산자를 수식에 추가`() {
        // given
        viewModel =
            CalculatorViewModel(calculator = calculator, getHistoriesUseCase = getHistoriesUseCase)
        viewModel.addTerm(1)
        assertEquals(viewModel.text.getOrAwaitValue(), "1")

        // when
        viewModel.addTerm(Operator.ADD)

        // then
        assertEquals(viewModel.text.getOrAwaitValue(), "1 +")
    }

    @Test
    fun `'1 + 2' 수식에서 마지막을 제거하면 '1 +'`() {
        // given
        val initialValue = OperationParser.parse("1 + 2")
        viewModel = CalculatorViewModel(initialValue, calculator, getHistoriesUseCase)
        assertEquals(viewModel.text.getOrAwaitValue(), "1 + 2")

        // when
        viewModel.removeTerm()

        // then
        assertEquals(viewModel.text.getOrAwaitValue(), "1 +")
    }

    @Test
    fun `'1 + 2' 수식을 계산하면 3`() = testScope.runTest {
        // given
        val initialValue = OperationParser.parse("1 + 2")
        viewModel = CalculatorViewModel(initialValue, calculator, getHistoriesUseCase)
        assertEquals("1 + 2", viewModel.text.getOrAwaitValue())

        // when
        viewModel.calculate()

        // then
        assertEquals("3", viewModel.text.getOrAwaitValue())
    }

    @Test
    fun `계산을 완료하면 기록이 추가된다`() {
        // given
        val initialValue = OperationParser.parse("1 + 2")
        viewModel = CalculatorViewModel(initialValue, calculator, getHistoriesUseCase)
        assertEquals("1 + 2", viewModel.text.getOrAwaitValue())

        // when
        viewModel.calculate()

        // then
        assertEquals(listOf(History("1 + 2", 3)), viewModel.histories.getOrAwaitValue())
    }

    @Test
    fun `처음 showHistory 값은 false다`() {
        // given
        viewModel =
            CalculatorViewModel(calculator = calculator, getHistoriesUseCase = getHistoriesUseCase)

        // then
        assertEquals(false, viewModel.showHistory.getOrAwaitValue())
    }

    @Test
    fun `토글하면 showHistory가 반대로된다`() {
        // given
        viewModel =
            CalculatorViewModel(calculator = calculator, getHistoriesUseCase = getHistoriesUseCase)
        assertEquals(false, viewModel.showHistory.getOrAwaitValue())
        // when
        viewModel.toggleHistory()
        // then
        assertEquals(true, viewModel.showHistory.getOrAwaitValue())
    }

    @Test
    fun `'1 +' 수식을 계산하면 에러`() {
        // given
        val initialValue = OperationParser.parse("1 +")
        viewModel = CalculatorViewModel(initialValue, calculator, getHistoriesUseCase)
        assertEquals(viewModel.text.getOrAwaitValue(), "1 +")

        // when
        viewModel.calculate()

        // then
        assertEquals(viewModel.exceptionMessage.getOrAwaitValue(), "완성되지 않은 수식입니다.")
    }

    @Test
    fun `0으로 나누면 에러`() {
        // given
        val initialValue = OperationParser.parse("1 / 0")
        viewModel = CalculatorViewModel(initialValue, calculator, getHistoriesUseCase)
        assertEquals(viewModel.text.getOrAwaitValue(), "1 / 0")

        // when
        viewModel.calculate()

        // then
        assertEquals(viewModel.exceptionMessage.getOrAwaitValue(), "0으로 나눌 수 없습니다.")
    }

    @Test
    fun `Int의 범위를 넘어서면 에러`() {
        // given
        val initialValue = OperationParser.parse("1111111111")
        viewModel = CalculatorViewModel(initialValue, calculator, getHistoriesUseCase)
        assertEquals(viewModel.text.getOrAwaitValue(), "1111111111")

        // when
        viewModel.addTerm(1)

        // then
        assertEquals(viewModel.exceptionMessage.getOrAwaitValue(), "숫자로 변환 불가능한 문자입니다.")
    }

    @Test
    fun `초기값을 주입한 뒤 외부의 Statement를 변경해도 내부의 값이 영향을 받지 않는다`() {
        // given

        val terms: MutableList<OperationTerm> = mutableListOf()
        viewModel = CalculatorViewModel(terms, calculator, getHistoriesUseCase)

        // when
        terms.add(Operand(1))
        viewModel.addTerm(1)

        // then
        assertEquals("1", viewModel.text.getOrAwaitValue())
    }
}
