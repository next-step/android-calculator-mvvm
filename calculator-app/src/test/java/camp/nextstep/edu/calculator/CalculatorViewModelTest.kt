package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.RecordRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CalculatorViewModelTest {

	@get:Rule
	val instantExecutorRule = InstantTaskExecutorRule()

	@get:Rule
	var mainCoroutineRule = MainCoroutineRule()

	private lateinit var calculatorViewModel: CalculatorViewModel
	private lateinit var recordRepository: RecordRepository

	@Before
	fun setUp() {
		recordRepository = mockk(relaxed = true)
		calculatorViewModel = CalculatorViewModel(recordRepository, mainCoroutineRule.testDispatcher)
	}

	@Test
	fun `빈 수식일 때, 피연산자를 추가하면, 피연산자가 추가된다`() {
		// when
		calculatorViewModel.addToExpression(1)

		// then
		val actual = calculatorViewModel.expression.getOrAwaitValue()
		assertThat(actual.toString()).isEqualTo("1")
	}

	@Test
	fun `빈 수식일 때, 연산자를 추가하면, 연산자가 추가되지 않는다`() {
		// when
		calculatorViewModel.addToExpression(Operator.Plus)

		// then
		val actual = calculatorViewModel.expression.getOrAwaitValue()
		assertThat(actual.toString()).isEqualTo("")
	}

	@Test
	fun `마지막 수식 값이 피연산자일 때, 피연산자를 추가하면, 피연산자가 추가된다`() {
		// given
		calculatorViewModel.addToExpression(1)

		// when
		calculatorViewModel.addToExpression(2)

		// then
		val actual = calculatorViewModel.expression.getOrAwaitValue()
		assertThat(actual.toString()).isEqualTo("12")
	}

	@Test
	fun `마지막 수식 값이 연산자일 때, 피연산자를 추가하면, 피연산자가 추가된다`() {
		// given
		calculatorViewModel.addToExpression(1)
		calculatorViewModel.addToExpression(Operator.Plus)

		// when
		calculatorViewModel.addToExpression(2)

		// then
		val actual = calculatorViewModel.expression.getOrAwaitValue()
		assertThat(actual.toString()).isEqualTo("1 + 2")
	}

	@Test
	fun `마지막 수식 값이 피연산자일 때, 연산자를 추가하면, 연산자가 추가된다`() {
		// given
		calculatorViewModel.addToExpression(1)

		// when
		calculatorViewModel.addToExpression(Operator.Plus)

		// then
		val actual = calculatorViewModel.expression.getOrAwaitValue()
		assertThat(actual.toString()).isEqualTo("1 +")
	}

	@Test
	fun `마지막 수식 값이 연산자일 때, 연산자를 추가하면, 연산자가 교체된다`() {
		// given
		calculatorViewModel.addToExpression(1)
		calculatorViewModel.addToExpression(Operator.Plus)

		// when
		calculatorViewModel.addToExpression(Operator.Divide)

		// then
		val actual = calculatorViewModel.expression.getOrAwaitValue()
		assertThat(actual.toString()).isEqualTo("1 /")
	}

	@Test
	fun `빈 수식일 때, 마지막 수식 값을 제거하면, 빈 수식으로 유지된다`() {
		// when
		calculatorViewModel.removeLast()

		// then
		val actual = calculatorViewModel.expression.getOrAwaitValue()
		assertThat(actual.toString()).isEqualTo("")
	}

	@Test
	fun `마지막 수식 값이 피연산자일 때, 마지막 수식 값을 제거하면, 숫자 하나가 제거된다`() {
		// given
		calculatorViewModel.addToExpression(12)

		// when
		calculatorViewModel.removeLast()

		// then
		val actual = calculatorViewModel.expression.getOrAwaitValue()
		assertThat(actual.toString()).isEqualTo("1")
	}

	@Test
	fun `마지막 수식 값이 연산자일 때, 마지막 수식 값을 제거하면, 연산자가 제거된다`() {
		// given
		calculatorViewModel.addToExpression(1)
		calculatorViewModel.addToExpression(Operator.Plus)

		// when
		calculatorViewModel.removeLast()

		// then
		val actual = calculatorViewModel.expression.getOrAwaitValue()
		assertThat(actual.toString()).isEqualTo("1")
	}

	@Test
	fun `완성된 수식일 때, 계산을 하면, 결과 값을 반환한다`() {
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
	fun `완성된 수식일 때, 계산을 하면, 계산기록을 추가한다`() = runBlocking {
		// given
		calculatorViewModel.addToExpression(1)
		calculatorViewModel.addToExpression(Operator.Plus)
		calculatorViewModel.addToExpression(2)

		// when
		calculatorViewModel.calculate()

		// then
		coVerify { recordRepository.insert(any(), any()) }
	}

	@Test
	fun `미완성된 수식일 때, 계산을 하면, 수식 미완성에 대한 값을 Unit 으로 설정된다`() {
		// given
		calculatorViewModel.addToExpression(1)
		calculatorViewModel.addToExpression(Operator.Plus)

		// when
		calculatorViewModel.calculate()

		// then
		val actual = calculatorViewModel.expressionInCompleted.getOrAwaitValue()
		assertThat(actual).isEqualTo(Unit)
	}
}