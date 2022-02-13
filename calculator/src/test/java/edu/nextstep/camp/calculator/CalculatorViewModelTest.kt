package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator.Companion.IS_NOT_OPERATOR
import edu.nextstep.camp.calculator.domain.Calculator.Companion.IS_NOT_OR_BLANK
import edu.nextstep.camp.calculator.domain.Calculator.Companion.WRONG_INPUT
import edu.nextstep.camp.calculator.domain.model.CalculateResult
import edu.nextstep.camp.calculator.domain.model.RecordStatement
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CalculatorViewModelTest {
    @JvmField
    @RegisterExtension
    val instantTaskExecutorExtension = InstantTaskExecutorExtension()

    private lateinit var calculatorViewModel: CalculatorViewModel

    @BeforeEach
    fun setUp() {
        calculatorViewModel = CalculatorViewModel()
    }

    @Test
    fun `수식 1 을 입력하면 1이 보여야한다`() {
        // WHEN
        calculatorViewModel.appendOperand(1)

        // THEN
        assertThat(calculatorViewModel.statement.getOrAwaitValue()).isEqualTo("1")
    }

    @Test
    fun `수식 1 + 을 입력하면 1 + 이 보여야한다`() {
        // WHEN
        calculatorViewModel.appendOperand(1)
        calculatorViewModel.appendOperator("+")

        // THEN
        assertThat(calculatorViewModel.statement.getOrAwaitValue()).isEqualTo("1 +")
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
        assertThat(calculatorViewModel.statement.getOrAwaitValue()).isEqualTo("2")
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
        assertThat(calculatorViewModel.statement.getOrAwaitValue()).isEqualTo("1 + 2 -")
    }

    @Test
    fun `빈 수식 입력시 예외처리를 해야한다`() {
        // WHEN
        calculatorViewModel.calculateStatement()

        // THEN
        assertThat(calculatorViewModel.errorString.getOrAwaitValue()).isEqualTo(IS_NOT_OR_BLANK)
    }

    @Test
    fun `잘 못된 수식 입력시 예외처리를 해야한다`() {
        // WHEN
        calculatorViewModel.appendOperand(1)
        calculatorViewModel.appendOperator("+")
        calculatorViewModel.calculateStatement()

        // THEN
        assertThat(calculatorViewModel.errorString.getOrAwaitValue()).isEqualTo(WRONG_INPUT)
    }

    @Test
    fun `사칙 연산이 아닌 수식 입력시 예외처리를 해야한다`() {
        // WHEN
        calculatorViewModel.appendOperand(1)
        calculatorViewModel.appendOperator("$")
        calculatorViewModel.appendOperand(1)
        calculatorViewModel.calculateStatement()

        // THEN
        assertThat(calculatorViewModel.errorString.getOrAwaitValue()).isEqualTo(IS_NOT_OPERATOR)
    }

    @CsvSource(
        delimiter = '=',
        value = [
            "1 + 2=3",
            "2 * 4=8",
            "2 * 3 * 4=24"
        ]
    )
    @ParameterizedTest(name = "{0}을 계산하면 {0} = {1}이 저장된다")
    fun recordStatementTest(expression: String, result: String) {
        val statement = RecordStatement(
            expression = expression,
            calculateResult = CalculateResult(result)
        )
        expression.split(" ").map {
            if (it.toIntOrNull() == null) {
                calculatorViewModel.appendOperator(it)
            } else {
                calculatorViewModel.appendOperand(it.toInt())
            }
        }

        // WHEN
        calculatorViewModel.calculateStatement()

        // THEN
        assertThat(calculatorViewModel.recordStatement.getOrAwaitValue()).isEqualTo(statement)
    }
}
