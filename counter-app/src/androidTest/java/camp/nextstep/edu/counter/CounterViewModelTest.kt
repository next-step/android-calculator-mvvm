package camp.nextstep.edu.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var counterViewModel: CounterViewModel
    @Before
    fun setUp() {
        counterViewModel = CounterViewModel(Counter())
    }

    @Test
    fun `카운터를_증가시키면_숫자_데이터_홀더값을_1_증가시킨다`() {
        // given
        counterViewModel.incrementCounter()
        // when
        counterViewModel.incrementCounter()
        val expected = 2
        // then
        val actual = counterViewModel.number.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun `카운터를_감소시키면_숫자_데이터_홀더값을_1_감소시킨다`() {
        // given
        counterViewModel.incrementCounter()
        counterViewModel.incrementCounter()
        // when
        counterViewModel.decrementCounter()
        val expected = 1
        // then
        val actual = counterViewModel.number.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun `숫자_0_에서_카운터를_감소시키면_경고_메시지_이벤트를_방출한다`() {
        // given
        counterViewModel.incrementCounter()
        counterViewModel.decrementCounter()
        // when
        counterViewModel.decrementCounter()
        val expected = "0 이하로 내릴 수 없습니다"
        // then
        val actual = counterViewModel.showWarningMessageEvent.getOrAwaitValue()
        assertEquals(expected, actual)
    }
}
