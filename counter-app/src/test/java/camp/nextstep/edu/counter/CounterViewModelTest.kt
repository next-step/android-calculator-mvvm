package camp.nextstep.edu.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var counterViewModel: CounterViewModel

    @Before
    fun setUp() {
        counterViewModel = CounterViewModel()
    }

    @Test
    fun `1일때_up_버튼을_누르면_2가_된다`() {
        // given
        counterViewModel.upCount()

        // when
        counterViewModel.upCount()

        // then
        val actual = counterViewModel.count.value
        assertEquals(2, actual)
    }

    @Test
    fun `1일때_down_버튼을_누르면_0이_된다`() {
        // given
        counterViewModel.upCount()

        // when
        counterViewModel.downCount()

        // then
        val actual = counterViewModel.count.value
        assertEquals(0, actual)
    }

    @Test
    fun `0일때_down_버튼을_누르면_변화가_없다`() {
        // when
        counterViewModel.downCount()

        // then
        val actual = counterViewModel.count.value
        assertEquals(0, actual)
    }
}