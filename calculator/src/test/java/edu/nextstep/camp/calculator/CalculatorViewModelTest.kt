package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.data.HistoryRepository
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantExecutorExtension::class)
internal class CalculatorViewModelTest {

    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var calculator: Calculator
    private lateinit var repository: HistoryRepository

    @BeforeEach
    fun setUp() {
        calculator = Calculator()
        repository = mockk(relaxed = true)
        calculatorViewModel =
            CalculatorViewModel(calculator = calculator, historyRepository = repository)
    }

    @AfterEach
    fun terminate() {
        Dispatchers.resetMain()
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when
        calculatorViewModel.addToExpression(5)

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(Expression(listOf(5)))
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(5)

        // when
        calculatorViewModel.addToExpression(Operator.Plus)

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(Expression(listOf(5, Operator.Plus)))
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(5)
        calculatorViewModel.addToExpression(Operator.Plus)

        // when
        calculatorViewModel.removeLast()

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(Expression(listOf(5)))
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(5)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(5)

        // when
        calculatorViewModel.calculate()

        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(Expression(listOf(10)))
    }

    @Test
    fun `계산을 완료했을 시 데이터가 추가된다`() {
        // given
        calculatorViewModel.addToExpression(5)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(5)

        // when
        calculatorViewModel.calculate()

        // then
        coVerify { repository.addHistory(any()) }
    }

}