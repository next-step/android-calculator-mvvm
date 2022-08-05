package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.data.EvaluationRecordDao
import edu.nextstep.camp.calculator.data.EvaluationRecordEntity
import edu.nextstep.camp.calculator.data.repository.EvaluationRecordRepositoryImpl
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.model.EvaluationRecord
import edu.nextstep.camp.calculator.domain.model.Expression
import edu.nextstep.camp.calculator.domain.model.Operator
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var calculator: Calculator
    private lateinit var dao: EvaluationRecordDao

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        calculator = Calculator()
        dao = mockk()
        viewModel = CalculatorViewModel(EvaluationRecordRepositoryImpl(dao, StandardTestDispatcher()))
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        viewModel.addToExpression(1)

        // then
        val expected = Expression(listOf(1))
        val actual = (viewModel.displayState.value as? CalculatorViewModel.State.ShowExpression)?.expression
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        // then
        val expected = Expression(listOf(1, Operator.Plus))
        val actual = (viewModel.displayState.value as? CalculatorViewModel.State.ShowExpression)?.expression
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // when
        viewModel.addToExpression(1)
        viewModel.removeLast()

        // then
        val expected = Expression.EMPTY
        val actual = (viewModel.displayState.value as? CalculatorViewModel.State.ShowExpression)?.expression
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)

        // when
        viewModel.calculate()


        // then
        val expected = Expression(listOf(3))
        val actual = (viewModel.displayState.value as? CalculatorViewModel.State.ShowExpression)?.expression
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `완성되지 않은 수식에 대해 계산이 실행되면 완성되지 않은 수식 에러가 발생한다`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.calculate()


        // then
        val expected = CalculatorViewModel.SideEffect.IncompleteExpressionError
        assertThat(viewModel.sideEffect.value?.consume()).isEqualTo(expected)
    }

    @Test
    fun `0으로 나누는 수식에 대해 계산이 실행되면 0으로 나누는 수식 에러가 발생한다`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Divide)
        viewModel.addToExpression(0)

        // when
        viewModel.calculate()


        // then
        val expected = CalculatorViewModel.SideEffect.DivideByZeroError
        assertThat(viewModel.sideEffect.value?.consume()).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 있을때 지우기를 누르면 마지막으로 입력된 연산자 혹은 피연산자가 지워진다`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Divide)
        viewModel.addToExpression(1)

        // when
        viewModel.removeLast()


        // then
        val expected = Expression(listOf(1, Operator.Divide))
        val actual = (viewModel.displayState.value as? CalculatorViewModel.State.ShowExpression)?.expression
        assertThat(actual).isEqualTo(expected)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `계산기 화면이 보일때, 히스토리 버튼을 누르면 계산 기록이 보인다`() = runTest {
        // given
        every { dao.getAll() } returns emptyList()

        // when
        viewModel.toggleHistoryBtn()
        advanceUntilIdle()

        // then
        val expected = CalculatorViewModel.State.ShowHistory(emptyList())
        val actual = viewModel.displayState.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `계산 기록 화면이 보일 때, 히스토리 버튼을 누르면 계산기 화면이 보인다`() = runTest {
        // given
        viewModel.toggleHistoryBtn()

        // when
        viewModel.toggleHistoryBtn()
        advanceUntilIdle()

        // then
        val expected = CalculatorViewModel.State.ShowExpression(Expression.EMPTY)
        val actual = viewModel.displayState.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `계산기록이 있을때, 히스토리 버튼을 누르면, 성공한 계산 기록이 보인다`() = runTest {
        // given
        every { dao.getAll() } returns listOf(EvaluationRecordEntity(id = 1, expression = "1 + 1", result = "1"))

        // when
        viewModel.toggleHistoryBtn()
        advanceUntilIdle()

        // then
        val expected = CalculatorViewModel.State.ShowHistory(listOf(EvaluationRecord("1 + 1", "1")))
        val actual = viewModel.displayState.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }
}
