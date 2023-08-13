package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.repository.MemoryRepository
import com.google.common.truth.Truth
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: MemoryRepository

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        repository = mockk()
        val testScheduler = TestCoroutineScheduler()
        val testDispatcher = StandardTestDispatcher(testScheduler)
        viewModel = CalculatorViewModel(dispatchers = testDispatcher, memoryRepository = repository)
    }

    @Test
    fun `입력없음 1 입력 1확인`() {
        viewModel.addToExpression(1)

        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `5 + 1 입력  5 + 1 확인`() {
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)

        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("5 + 1")
    }

    @Test
    fun `89 입력  89 확인`() {
        viewModel.addToExpression(8)
        viewModel.addToExpression(9)

        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("89")
    }

    @Test
    fun `입력없음 + 입력 빈문자 확인`() {
        viewModel.addToExpression(Operator.Plus)

        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("")
    }

    @Test
    fun `1 + 입력 1 + 확인`() {
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("1 +")
    }

    @Test
    fun `1 +  - 입력 1 - 확인`() {
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(Operator.Minus)

        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("1 -")
    }

    @Test
    fun `입력 없음 - 입력 빈문자 확인`() {
        viewModel.addToExpression(Operator.Minus)

        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("")
    }

    @Test
    fun `32 + 1 입력 지우기 32 + 확인`() {
        viewModel.addToExpression(3)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)

        viewModel.removeLast()
        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("32 +")
    }

    @Test
    fun `32 + 입력 지우기 32 확인`() {
        viewModel.addToExpression(3)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)

        viewModel.removeLast()
        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("32")
    }

    @Test
    fun `32 입력 지우기 3 확인`() {
        viewModel.addToExpression(3)
        viewModel.addToExpression(2)

        viewModel.removeLast()
        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("3")
    }

    @Test
    fun `3 입력 지우기 빈문자 확인`() {
        viewModel.addToExpression(3)

        viewModel.removeLast()
        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("")
    }

    @Test
    fun `3 + 2 입력 계산 5 확인`() {

        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)

        viewModel.calculate()
        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("5")
    }

    @Test
    fun `3 +  입력 계산  입력된 문자 그대로 있는지 확인`() {
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)

        viewModel.calculate()
        val actual = viewModel.expression.value?.toString()

        Truth.assertThat(actual).isEqualTo("3 +")
    }
}
