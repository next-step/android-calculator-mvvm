package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import com.nextstep.camp.calculator.data.Record
import com.nextstep.camp.calculator.data.RecordsRepository
import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringExpressionState
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class CalculatorViewModelTest {

    @JvmField
    @RegisterExtension
    val instantTaskExecutorExtension = InstantTaskExecutorExtension()

    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var recordsRepository: RecordsRepository

    @BeforeEach
    fun setUp() {
        recordsRepository = mockk(relaxed = true)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        calculatorViewModel = CalculatorViewModel(
            recordsRepository = recordsRepository,
            state = StringExpressionState.EmptyState(),
        )

        // when
        calculatorViewModel.addElement(Operand(10))

        // then
        assertThat(calculatorViewModel.state.getOrAwaitValue()).isEqualTo(
            StringExpressionState.of("10")
        )
    }

    @Test
    fun `피연산자가 입력되어 있을때, 연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        calculatorViewModel = CalculatorViewModel(
            recordsRepository = recordsRepository,
            state = StringExpressionState.of("1")
        )

        // when
        calculatorViewModel.addElement(Operator.PLUS)

        // then
        assertThat(calculatorViewModel.state.getOrAwaitValue()).isEqualTo(
            StringExpressionState.of("1 +")
        )
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        calculatorViewModel = CalculatorViewModel(
            recordsRepository = recordsRepository,
            state = StringExpressionState.of("1")
        )

        // when
        calculatorViewModel.removeElement()

        // then
        assertThat(calculatorViewModel.state.getOrAwaitValue()).isEqualTo(
            StringExpressionState.EmptyState()
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `계산이 실행되면 계산기에 의해 계산된 결과를 화면에 보여주고 기록을 저장해야 한다`() {
        // given
        val givenExpression = "1 + 2"
        calculatorViewModel = CalculatorViewModel(
            recordsRepository = recordsRepository,
            state = StringExpressionState.of(givenExpression),
            dispatcher = UnconfinedTestDispatcher()
        )

        // when
        calculatorViewModel.calculate()

        // then
        assertThat(calculatorViewModel.state.getOrAwaitValue()).isEqualTo(
            StringExpressionState.of("3")
        )
        assertThat(calculatorViewModel.records.getOrAwaitValue().size).isEqualTo(1)
        coVerify { recordsRepository.insert(Record(expression = givenExpression, result = 3.0)) }
    }

    @Test
    fun `완전하지 않은 형식의 계산이 실행되면 실패 토스트 메시지를 보여줘야 한다`() {
        // given
        calculatorViewModel = CalculatorViewModel(
            recordsRepository = recordsRepository,
            state = StringExpressionState.of("1 +")
        )

        // when
        calculatorViewModel.calculate()

        // then
        assertThat(calculatorViewModel.calculationFailed.getOrAwaitValue()).isEqualTo(true)
    }

    @Test
    fun `계산 기록이 안보이는 상태에서 시계 버튼을 누르면 계산 기록 UI가 사라져야 한다`() {
        // given
        calculatorViewModel = CalculatorViewModel(
            recordsRepository = recordsRepository,
            shouldShowRecords = false
        )

        // when
        calculatorViewModel.toggleRecords()

        // then
        assertThat(calculatorViewModel.showRecords.getOrAwaitValue()).isEqualTo(true)
    }

    @Test
    fun `계산 기록이 보이는 상태에서 시계 버튼을 누르면 계산 기록 UI가 사라져야 한다`() {
        // given
        calculatorViewModel = CalculatorViewModel(
            recordsRepository = recordsRepository,
            shouldShowRecords = true
        )

        // when
        calculatorViewModel.toggleRecords()

        // then
        assertThat(calculatorViewModel.showRecords.getOrAwaitValue()).isEqualTo(false)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `화면이 초기화될때 저장된 기록을 불러와야 한다`() {
        // given
        calculatorViewModel = CalculatorViewModel(
            recordsRepository = recordsRepository,
            dispatcher = UnconfinedTestDispatcher()
        )

        // then
        coVerify { recordsRepository.getAll() }
    }
}
