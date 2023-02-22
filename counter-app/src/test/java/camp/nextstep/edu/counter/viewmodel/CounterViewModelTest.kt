package camp.nextstep.edu.counter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {
    private lateinit var counterViewModel: CounterViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        counterViewModel = CounterViewModel()
    }

    @Test
    fun `0일 경우 UP 버튼을 한 번 누르면 1이 된다`() {
        // given

        // when
        counterViewModel.up()

        // then
        val actual = counterViewModel.count.value
        assertEquals(1, actual)
    }

    @Test
    fun `1일 경우 UP 버튼을 한 번 누르면 2가 된다`() {
        // given
        counterViewModel.up()

        // when
        counterViewModel.up()

        // then
        val actual = counterViewModel.count.value
        assertEquals(2, actual)
    }

    @Test
    fun `1일 경우 DOWN 버튼을 한 번 누르면 0이 된다`() {
        // given
        counterViewModel.up()

        // when
        counterViewModel.down()

        // then
        val actual = counterViewModel.count.value
        assertEquals(0, actual)
    }

    @Test
    fun `2일 경우 DOWN 버튼을 두 번 누르면 0이다`() {
        // given
        counterViewModel.up()
        counterViewModel.up()

        // when
        counterViewModel.down()
        counterViewModel.down()

        // then
        val actual = counterViewModel.count.value
        assertEquals(0, actual)
    }

    @Test
    fun `0일 경우 DOWN 버튼을 누르면 변화 없이 0이다`() {
        // given

        // when
        counterViewModel.down()

        // then
        val actual = counterViewModel.count.value
        assertEquals(0, actual)
    }
}