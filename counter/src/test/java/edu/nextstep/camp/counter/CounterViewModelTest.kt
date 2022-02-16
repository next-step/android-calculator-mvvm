package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
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
    fun `increase 하면 숫자가 1 증가해야 한다`() {
        // when
        val expected = counterViewModel.count.getOrAwaitValue() + 1
        counterViewModel.increase()
        val actual = counterViewModel.count.getOrAwaitValue()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun `decrease 하면 숫자가 1 감소해야 한다`() {
        // given
        val default = 10
        counterViewModel = CounterViewModel(default)

        // when
        val expected = default - 1
        counterViewModel.decrease()
        val actual = counterViewModel.count.getOrAwaitValue()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun `숫자는 음수가 될 수 없다`() {
        // when
        val expected = 0
        counterViewModel.decrease()
        val actual = counterViewModel.count.getOrAwaitValue()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun `0일 때 decrease 하면 에러가 활성화 되어야 한다`() {
        // when
        counterViewModel.decrease()

        // then
        assertThat(counterViewModel.errorEvent.getOrAwaitValue())
            .isInstanceOf(Unit::class.java)
    }
}
