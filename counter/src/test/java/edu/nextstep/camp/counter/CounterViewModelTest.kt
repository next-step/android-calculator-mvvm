package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CounterViewModel

    @Before
    fun setup() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `0일 때 up을 하면 1이 된다`() {
        viewModel.upCount()
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(1)
    }

    @Test
    fun `2일 때 up을 하면 3이 된다`() {
        // given
        viewModel.upCount()
        viewModel.upCount()

        // when
        viewModel.upCount()

        // then
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(3)
    }

    @Test
    fun `3일 때 down을 하면 2가 된다`() {
        // given
        viewModel.upCount()
        viewModel.upCount()
        viewModel.upCount()

        // when
        viewModel.downCount()

        // then
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(2)
    }

    @Test
    fun `0일 때 down을 하면 변함없이 0이다`() {
        // when
        viewModel.downCount()

        // then
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(0)
    }
}
