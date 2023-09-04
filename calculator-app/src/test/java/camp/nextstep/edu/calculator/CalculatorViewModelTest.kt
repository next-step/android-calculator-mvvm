package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.ArithmeticOperator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Result
import camp.nextstep.edu.calculator.domain.ResultRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.properties.Delegates

class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var resultRepository: ResultRepository
    private lateinit var expression: String
    private var result by Delegates.notNull<Int>()

    @Before
    fun setUp() {
        resultRepository = mockk()
        coEvery {
            expression = any()
            result = any()
            resultRepository.putResult(expression, result)
        } just runs

        every { resultRepository.resultList } answers { flowOf(listOf(Result(0, expression, result))) }
    }

    @Test
    fun `수식이_비었을_때_피연산자를_입력하면_피연산자가_추가된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression(""), resultRepository)
        // when
        val input = 7
        calculatorViewModel.addOperand(input)
        val expected = Expression(input.toString())
        // then
        val actual = calculatorViewModel.expression.value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `피연산자가_있을_때_피연산자를_입력하면_피연산자가_추가된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7"), resultRepository)
        // when
        val input = 7
        calculatorViewModel.addOperand(input)
        val expected = "7$input"
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `연산자가_있을_때_피연산자를_입력하면_피연산자가_추가된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + "), resultRepository)
        // when
        val input = 7
        calculatorViewModel.addOperand(input)
        val expected = "7 + $input"
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `수식이_비었을_때_연산자를_입력하면_연산자가_추가되지_않는다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression(""), resultRepository)
        // when
        val input = ArithmeticOperator.PLUS
        calculatorViewModel.addOperator(input)
        val expected = ""
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `피연산자가_있을_때_연산자를_입력하면_연산자가_추가된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7"), resultRepository)
        // when
        val input = ArithmeticOperator.PLUS
        calculatorViewModel.addOperator(input)
        val expected = "7 ${input.value} "
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `연산자가_있을_때_연산자를_입력하면_연산자가_변경된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + "), resultRepository)
        // when
        val input = ArithmeticOperator.MULTIPLY
        calculatorViewModel.addOperator(input)
        val expected = "7 ${input.value} "
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `정상적인_수식일_때_Equal을_입력하면_수식을_계산한_결과값이_추가된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + 78"), resultRepository)
        // when
        calculatorViewModel.calculate()
        val expected = "85"
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `비정상적인_수식일_때_Equal을_입력하면_이전_수식이_그대로_존재한다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + 78 - "), resultRepository)
        // when
        calculatorViewModel.calculate()
        val expected = "7 + 78 - "
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `비정상적인_수식일_때_Equal을_입력하면_경고_메시지_이벤트가_트리거된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + 78 - "), resultRepository)
        // when
        calculatorViewModel.calculate()
        // then
        val actual = calculatorViewModel.showWarningMessageEvent.getOrAwaitValue()
        assertThat(actual).isNotEmpty()
    }

    @Test
    fun `연산자가_마지막에_있을_때_삭제를_입력하면_수식의_마지막_연산자가_지워진다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + 78 - "), resultRepository)
        // when
        calculatorViewModel.delete()
        val expected = "7 + 78"
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `피연산자가_마지막에_있을_때_삭제를_입력하면_수식의_마지막_피연산자의_숫자가_지워진다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("7 + 78"), resultRepository)
        // when
        calculatorViewModel.delete()
        val expected = "7 + 7"
        // then
        val actual = calculatorViewModel.expression.getOrAwaitValue().value
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `기록버튼을_누르면_기록화면_가시성이_바뀐다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression(""), resultRepository)
        // when
        calculatorViewModel.toggleResultListVisible()
        val expected = true
        // then
        val actual = calculatorViewModel.isResultListVisible.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Equal을_입력하면_결과값이_저장되고_기록화면에_값이_출력된다`() {
        // given
        calculatorViewModel = CalculatorViewModel(Expression("1 + 3 + 5"), resultRepository)
        // when
        calculatorViewModel.calculate()
        // then
        coVerify { resultRepository.putResult("1 + 3 + 5", 9) }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
