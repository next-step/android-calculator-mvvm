package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.counter.getOrAwaitValue
import edu.nextstep.camp.domain.calculator.CalculationRecord
import edu.nextstep.camp.domain.calculator.CalculationRecordsRepository
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

class CalculatorViewModelTest {

    @JvmField
    @RegisterExtension
    val instantTaskExecutorExtension = InstantTaskExecutorExtension()


    @ParameterizedTest(name = "입력 : {0}")
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9])
    internal fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`(value: Int) {
        //given
        val viewModel = CalculatorViewModel(
            calculationRecordsRepository = mockk(),
        )
        val expected = Expression(listOf(value))

        //when
        viewModel.onEvent(CalculatorEvent.operand(value))
        val actual = viewModel.onState.getOrAwaitValue().consume() as CalculatorState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }

    @ParameterizedTest
    @EnumSource(value = Operator::class)
    internal fun `숫자 뒤에 연산자가 입력되면 연산자가 추가되고 변경된 수식을 보여줘야 한다`(value: Operator) {
        //given
        val viewModel = CalculatorViewModel(
            calculationRecordsRepository = mockk(),
            expression = Expression(listOf(1))
        )
        val expected = Expression(listOf(1, value))

        //when
        viewModel.onEvent(CalculatorEvent.AddOperator(value))
        val actual = viewModel.onState.getOrAwaitValue().consume() as CalculatorState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }

    @Test
    internal fun `숫자 뒤에 숫자가 입력되면 변경된 숫자를 보여줘야 한다`() {
        //given
        val viewModel = CalculatorViewModel(
            calculationRecordsRepository = mockk(),
            expression = Expression(listOf(123))
        )
        val expected = Expression(listOf(1239))

        //when
        viewModel.onEvent(CalculatorEvent.operand(9))
        val actual = viewModel.onState.getOrAwaitValue().consume() as CalculatorState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }


    @Test
    internal fun `연산자가 입력된 상태에서 연산자를 추가 입력하면 마지막으로 입력된 연산자로 변경된다`() {
        //given
        val viewModel = CalculatorViewModel(
            calculationRecordsRepository = mockk(),
            expression = Expression(listOf(1, Operator.Plus))
        )
        val expected = Expression(listOf(1, Operator.Multiply))

        //when
        viewModel.onEvent(CalculatorEvent.AddOperator(Operator.Minus))
        viewModel.onEvent(CalculatorEvent.AddOperator(Operator.Plus))
        viewModel.onEvent(CalculatorEvent.AddOperator(Operator.Divide))
        viewModel.onEvent(CalculatorEvent.AddOperator(Operator.Multiply))
        val actual = viewModel.onState.getOrAwaitValue().consume() as CalculatorState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }

    @Test
    internal fun `수식이 완성되지 않은 상태에서 계산을 하면 변화가 없다`() {
        //given
        val viewModel = CalculatorViewModel(
            calculationRecordsRepository = mockk(),
            expression = Expression(listOf(3, Operator.Multiply, 90, Operator.Minus))
        )

        val expected = CalculatorState.ShowIncompleteExpressionError

        //when
        viewModel.onEvent(CalculatorEvent.Calculate)
        val actual = viewModel.onState.getOrAwaitValue().consume() as CalculatorState.ShowIncompleteExpressionError

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `수식이 완성된 상태에서 계산을 하면 수식의 결과를 보여줘야 한다`() {
        //given
        val expected = 270
        val viewModel = CalculatorViewModel(
            calculationRecordsRepository = mockk(),
            expression = Expression(listOf(3, Operator.Multiply, 90))
        )

        //when
        viewModel.onEvent(CalculatorEvent.Calculate)
        val actual = viewModel.onState.getOrAwaitValue().consume() as CalculatorState.ShowResult

        //then
        assertThat(actual.result).isEqualTo(expected)
    }

    @Test
    internal fun `수식이 입력된 상태에서 삭제 버튼을 누르면 마지막 입력된 숫자 또는 연산자가 삭제된다`() {
        //given
        val expected = Expression(listOf(1, Operator.Plus, 3))
        val viewModel = CalculatorViewModel(
            calculationRecordsRepository = mockk(),
            expression = Expression(listOf(1, Operator.Plus, 32))
        )

        //when
        viewModel.onEvent(CalculatorEvent.RemoveLast)
        val actual = viewModel.onState.getOrAwaitValue().consume() as CalculatorState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    internal fun `계산기록 상태가 아닐 때 계산기록 버튼 이벤트를 발생되면 계산기록 상태가 된다`() {
        //given
        val calculationRecord = CalculationRecord(Expression(listOf(1, Operator.Plus, 32)), 33)
        val expected: List<CalculationRecord> = listOf(calculationRecord)
        val repository = object : CalculationRecordsRepository {
            override suspend fun saveCalculationRecord(vararg records: CalculationRecord) {}
            override suspend fun loadCalculationRecords(): List<CalculationRecord> = listOf(CalculationRecord(Expression(listOf(1, Operator.Plus, 32)), 33))
        }

        val viewModel = CalculatorViewModel(
            dispatcher = UnconfinedTestDispatcher(),
            isShowingHistory = false,
            calculationRecordsRepository = repository
        )

        //when
        viewModel.onEvent(CalculatorEvent.ToggleCalculatorHistory)
        val actual = viewModel.onState.getOrAwaitValue().consume() as CalculatorState.LoadedCalculatorHistory

        //then
        assertThat(actual.records).isEqualTo(expected)
    }

    @Test
    internal fun `계산기록 상태에서 계산기록 버튼 이벤트가 발생되면 계산진행 상태가 된다`() {
        //given
        val expected = Expression(listOf(1, Operator.Plus, 32))
        val repository = object : CalculationRecordsRepository {
            override suspend fun saveCalculationRecord(vararg records: CalculationRecord) {}
            override suspend fun loadCalculationRecords(): List<CalculationRecord> = emptyList()
        }
        val viewModel = CalculatorViewModel(
            expression = expected,
            isShowingHistory = true,
            calculationRecordsRepository = repository
        )

        //when
        viewModel.onEvent(CalculatorEvent.ToggleCalculatorHistory)
        val actual = viewModel.onState.getOrAwaitValue().consume() as CalculatorState.ShowExpression

        //then
        assertThat(actual.expression).isEqualTo(expected)
    }

    @Test
    internal fun `계산진행 상태에서 연산 상태가 발생되면 연산은 저장되어야 한다`() {
        //given
        val expected = CalculationRecord(Expression(listOf(11, Operator.Multiply, 3)), 33)
        val repository: CalculationRecordsRepository = mockk(relaxed = true)
        val viewModel = CalculatorViewModel(
            expression = Expression(listOf(11, Operator.Multiply, 3)),
            isShowingHistory = true,
            calculationRecordsRepository = repository
        )

        //when
        viewModel.onEvent(CalculatorEvent.Calculate)

        //then
        coVerify { repository.saveCalculationRecord(expected) }
    }
}
