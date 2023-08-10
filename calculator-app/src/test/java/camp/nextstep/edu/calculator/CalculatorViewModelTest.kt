package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.model.History
import camp.nextstep.edu.calculator.domain.repository.HistoryRepository
import camp.nextstep.edu.calculator.domain.usecase.GetCalculateHistoriesUseCase
import camp.nextstep.edu.calculator.domain.usecase.PostCalculateUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        val repository: HistoryRepository = mockk(relaxed = true)
        val getCalculateHistoriesUseCase = GetCalculateHistoriesUseCase(repository)
        val postCalculateUseCase = PostCalculateUseCase(repository)
        viewModel = CalculatorViewModel(postCalculateUseCase = postCalculateUseCase, getCalculateHistoriesUseCase = getCalculateHistoriesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `1을_누르면_1이_보인다`() {
        // when: 1을 누르면
        viewModel.addToExpression(1)

        // then: 1이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `12을_누르면_12이_보인다`() {
        // when: 12을 누르면
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)

        // then: 12이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("12")
    }

    @Test
    fun `12_더하기를_누르면_12더하기가_보인다`() {
        // when: 12 +을 누르면
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)

        // then: 12 + 이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("12 +")
    }

    @Test
    fun `12더하기을_누르고_12를_누르면_12더하기12이_보인다`() {
        // given: 12 +
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)

        // when: 12를 누르면
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)

        // then: 12 + 12이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("12 + 12")
    }

    @Test
    fun `12더하기12을_누르고_결과를_누르면_24_보인다`() {
        // given: 12 + 12
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)

        // when: 결과를 누르면
        viewModel.calculate()

        // then: 24이 나온다
        testScope.launch {
            val actual = viewModel.text.getOrAwaitValue()
            assertThat(actual).isEqualTo("24")
        }
    }

    @Test
    fun `12을_누르고_지우기_누르면_1이_보인다`() {
        // given: 12을 누르면
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)

        // when: 지우기를 누르면
        viewModel.removeLast()

        // then: 1이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `초기상태에서_더하기를_누르면_동작을_안한다`() {
        // when: +를 누르면
        viewModel.addToExpression(Operator.Plus)

        // then: 동작을 안한다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `초기상태에서_historyVisible_값은_False다`() {
        // given: 초기 상태

        // then: historyVisible값은 false다
        val actual = viewModel.historyVisible.getOrAwaitValue()
        assertThat(actual).isEqualTo(false)
    }

    @Test
    fun `초기상태에서_메모리_버튼을_누르면_historyVisible_값은_true다`() {
        // given: 초기 상태

        // when: 메모리 버튼을 누르면
        viewModel.toggleHistory()

        // then: historyVisible값은 true다
        val actual = viewModel.historyVisible.getOrAwaitValue()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `계산을_완료하고_메모리_버튼을_누르면_기록에_추가된다`() {
        // given: 12 + 12 = 를 누르고
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)
        viewModel.calculate()

        // when: 메모리 버튼을 누르면
        viewModel.toggleHistory()

        // then: 12 + 12 = 의 기록이 추가된다.
        testScope.launch {
            assertEquals(listOf(History(expressions = "12 + 12", result = 24)), viewModel.histories.getOrAwaitValue())
        }
    }

    @Test
    fun `초기상태에서_메모리_버튼을_두번_누르면_historyVisible_값은_true다`() {
        // given: 초기 상태

        // when: 메모리 버튼을 두번 누르면
        viewModel.toggleHistory()
        viewModel.toggleHistory()

        // then: historyVisible값은 false다
        val actual = viewModel.historyVisible.getOrAwaitValue()
        assertThat(actual).isEqualTo(false)
    }
}