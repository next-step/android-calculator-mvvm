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
    fun setUp() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `count가 0일 때 증가하면 1이 된다`() {
        // WHEN
        viewModel.upCount()

        // THEN
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(1)
    }

    @Test
    fun `count가 1일 때 감소하면 0이 된다`() {
        // GIVEN
        viewModel.upCount()

        // WHEN
        viewModel.downCount()

        // THEN
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(0)
    }

    @Test
    fun `count가 0일 때 감소하면 그대로 0이 된다`() {
        // WHEN
        viewModel.downCount()

        // THEN
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(0)
    }
}
