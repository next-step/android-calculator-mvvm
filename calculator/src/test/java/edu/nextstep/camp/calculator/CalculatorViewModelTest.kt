package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.MainCoroutineRule
import edu.nextstep.camp.domain.calculator.CalculationHistory
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator
import edu.nextstep.camp.domain.calculator.usecase.GetAllCalculationHistoryUseCase
import edu.nextstep.camp.domain.calculator.usecase.InsertCalculationHistoryUseCase
import edu.nextstep.camp.getOrAwaitValue
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@Suppress("NonAsciiCharacters")
class CalculatorViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var calculatorViewModel: CalculatorViewModel

    @MockK(relaxed = true)
    private lateinit var insertCalculationHistoryUseCase: InsertCalculationHistoryUseCase

    @MockK(relaxed = true)
    private lateinit var getAllCalculationHistoryUseCase: GetAllCalculationHistoryUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        every {
            getAllCalculationHistoryUseCase()
        } returns flow {
            emit(dummyCalculationHistoryList)
        }

        calculatorViewModel = CalculatorViewModel(
            insertCalculationHistoryUseCase,
            getAllCalculationHistoryUseCase
        )
    }

    @Test
    fun `뷰 모델 초기화시 저장되어 있던 모든 연산 기록을 불러와야 한다`() {
        verify { getAllCalculationHistoryUseCase() }

        val actual = calculatorViewModel.calculationHistoryList.getOrAwaitValue()
        assertThat(actual).isEqualTo(dummyCalculationHistoryList)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addToExpression(1)

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("1")
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("1 +")
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.removeLast()

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("")
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(2)

        // when
        calculatorViewModel.calculate()

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual.toString()).isEqualTo("3")
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 저장해야 한다`() {
        // given
        val expectedExpression = Expression.EMPTY + 1 + Operator.Plus + 2

        coEvery {
            insertCalculationHistoryUseCase(CalculationHistory(CalculationHistory.DEFAULT_ID, expectedExpression.toString(), 3))
        } just runs

        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(2)

        // when
        calculatorViewModel.calculate()

        // then
        coVerify {
            insertCalculationHistoryUseCase(CalculationHistory(CalculationHistory.DEFAULT_ID, expectedExpression.toString(), 3))
        }
    }

    @Test
    fun `연산식이 완성되지 않은 상태에서 계산이 실행되면 완성되지 않은 수식이라는 에러 메시지를 화면에 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)

        // when
        calculatorViewModel.calculate()

        // then
        val actual = calculatorViewModel.errorMessage.getOrAwaitValue()
        assertThat(actual).isEqualTo(UiText.StringResource(R.string.incomplete_expression))
    }

    @Test
    fun `최초에 계산기 모드로 진입해야 한다`() {
        val actual = calculatorViewModel.uiMode.getOrAwaitValue()
        assertThat(actual).isEqualTo(CalculatorUiMode.CALCULATOR)
    }

    @Test
    fun `현재 계산기 모드에서 시계 버튼을 눌러 화면 모드를 변경하는 경우 계산 기록 모드로 변경되어야 한다`() {
        // when
        calculatorViewModel.toggleUiBetweenCalculatorOrHistory()

        // then
        val actual = calculatorViewModel.uiMode.getOrAwaitValue()
        assertThat(actual).isEqualTo(CalculatorUiMode.CALCULATION_HISTORY)
    }

    @Test
    fun `현재 계산 기록 모드에서 시계 버튼을 눌러 화면 모드를 변경하는 경우 계산기 모드로 변경되어야 한다`() {
        // given
        calculatorViewModel.toggleUiBetweenCalculatorOrHistory()

        // when
        calculatorViewModel.toggleUiBetweenCalculatorOrHistory()

        // then
        val actual = calculatorViewModel.uiMode.getOrAwaitValue()
        assertThat(actual).isEqualTo(CalculatorUiMode.CALCULATOR)
    }

    companion object {
        private val dummyCalculationHistoryList = listOf(
            CalculationHistory(1, "1 + 1", 2),
            CalculationHistory(2, "1 - 1", 0),
            CalculationHistory(3, "1 * 1", 1),
        )
    }
}