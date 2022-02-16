package edu.nextstep.camp.calculator

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator.Companion.IS_NOT_OPERATOR
import edu.nextstep.camp.calculator.domain.Calculator.Companion.IS_NOT_OR_BLANK
import edu.nextstep.camp.calculator.domain.Calculator.Companion.WRONG_INPUT
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.di.ExpressionInjector
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(CoroutinesTestExtension::class)
class CalculatorViewModelTest {

    private lateinit var calculatorViewModel: CalculatorViewModel

    @BeforeEach
    fun setUp() {
        val repository: CalculatorRepository = mockk(relaxed = true)
        calculatorViewModel =
            CalculatorViewModel(ExpressionInjector.providesExpression(), repository)
    }

    @Test
    fun `수식 1 을 입력하면 1 이 보여야한다`() {
        // WHEN
        calculatorViewModel.appendOperand(1)

        // THEN
        assertThat(calculatorViewModel.statement.value).isEqualTo("1")
    }

    @Test
    fun `수식 1 + 을 입력하면 1 + 이 보여야한다`() = runBlocking {
        // WHEN
        calculatorViewModel.appendOperand(1)
        calculatorViewModel.appendOperator("+")

        // THEN
        assertThat(calculatorViewModel.statement.value).isEqualTo("1 +")
    }

    @Test
    fun `수식 1 + 2 - 1 을 입력하고 계산하면 1이 보여야한다`() {
        // WHEN
        calculatorViewModel.appendOperand(1)
        calculatorViewModel.appendOperator("+")
        calculatorViewModel.appendOperand(2)
        calculatorViewModel.appendOperator("-")
        calculatorViewModel.appendOperand(1)
        calculatorViewModel.calculateStatement()

        // THEN
        assertThat(calculatorViewModel.statement.value).isEqualTo("2")
    }

    @Test
    fun `수식 1 + 2 - 1 에서 요소를 제거하면 1 + 2 - 가 보여야한다`() {
        // WHEN
        calculatorViewModel.appendOperand(1)
        calculatorViewModel.appendOperator("+")
        calculatorViewModel.appendOperand(2)
        calculatorViewModel.appendOperator("-")
        calculatorViewModel.appendOperand(1)
        calculatorViewModel.deleteLastElement()

        // THEN
        assertThat(calculatorViewModel.statement.value).isEqualTo("1 + 2 -")
    }

    @Test
    fun `빈 수식 입력시 예외처리를 해야한다`() = runBlocking {
        // WHEN
        val job = launch(start = CoroutineStart.LAZY) {
            calculatorViewModel.calculateStatement()
        }

        calculatorViewModel.errorMessage.test {
            job.start()
            assertEquals(IS_NOT_OR_BLANK, awaitItem())
        }
    }

    @Test
    fun `잘 못된 수식 입력시 예외처리를 해야한다`() = runBlocking {
        // WHEN
        val job = launch(start = CoroutineStart.LAZY) {
            calculatorViewModel.appendOperand(1)
            calculatorViewModel.appendOperator("+")
            calculatorViewModel.calculateStatement()
        }

        // THEN
        calculatorViewModel.errorMessage.test {
            job.start()
            assertEquals(WRONG_INPUT, awaitItem())
        }
    }

    @Test
    fun `사칙 연산이 아닌 수식 입력시 예외처리를 해야한다`() = runBlocking {
        // WHEN
        val job = launch(start = CoroutineStart.LAZY) {
            calculatorViewModel.appendOperand(1)
            calculatorViewModel.appendOperator("$")
            calculatorViewModel.appendOperand(1)
            calculatorViewModel.calculateStatement()
        }
        // THEN
        calculatorViewModel.errorMessage.test {
            job.start()
            assertEquals(IS_NOT_OPERATOR, awaitItem())
        }
    }

    @Test
    fun `기록 버튼이 클릭되면 화면을 보여준다`() {
        // WHEN
        calculatorViewModel.toggleMemoryView()

        // THEN
        assertThat(calculatorViewModel.memoryViewVisibility.value).isEqualTo(true)
    }
}
