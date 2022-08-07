package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.*
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var repository: HistoryRepository

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `입력된 피연산자가 없을 때 사용자가 숫자 버튼을 누르면 해당 숫자가 보여야 한다`() {
        // when
        viewModel = CalculatorViewModel(
            Expression(),
            Calculator(),
            repository
        )
        viewModel.addToExpression(1)

        // then
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 버튼을 누르면 해당 기호가 보여야 한다`() {
        // given
        viewModel = CalculatorViewModel(
            Expression(listOf(1)),
            Calculator(),
            repository
        )

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("1 +")
    }

    @Test
    fun `입력된 수식이 있을 때, 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        // given
        viewModel = CalculatorViewModel(
            Expression(listOf(1, Operator.Plus)),
            Calculator(),
            repository
        )

        // when
        viewModel.removeLast()

        // then
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `입력된 수신이 완전할 때, 등호 버튼을 누르면 입력된 수식의 결과가 보여야 한다`() {
        // given
        viewModel = CalculatorViewModel(
            Expression(listOf(1, Operator.Plus, 2)), Calculator(),
            repository
        )

        // when
        viewModel.calculate()

        // then
        val actual = viewModel.expression.value.toString()
        assertThat(actual).isEqualTo("3")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 등호 버튼을 눌렀을 때 오류 메시지 이벤트가 발생한다`() {
        // given
        viewModel = CalculatorViewModel(
            Expression(listOf(1, Operator.Plus)), Calculator(),
            repository
        )

        // when
        viewModel.calculate()

        // then
        val actual = viewModel.errorEvent.value
        assertThat(actual).isEqualTo(Event.CalculatorError)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `수식의 결과가 계산 기록에 저장된다`() = runTest {
        // given
        viewModel =
            CalculatorViewModel(Expression(listOf(1, Operator.Plus, 1)), Calculator(), repository)

        // when

        viewModel.calculate()
        advanceUntilIdle()

        // then
        coVerify { repository.insertHistory(History("1 + 1", 2)) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `저장된 계산 기록을 가져온다`() = runTest {
        // given
        viewModel =
            CalculatorViewModel(Expression(listOf(1, Operator.Plus, 1)), Calculator(), repository)

        // when

        viewModel.toggleHistory()
        advanceUntilIdle()

        // then
        coVerify { repository.getHistoryList() }
    }
}