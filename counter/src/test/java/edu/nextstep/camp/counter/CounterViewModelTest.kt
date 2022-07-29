package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

/**
 * Created by link.js on 2022. 07. 28..
 */
class CounterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `0에서 increment 하면 1이 된다`() {
        val viewModel = CounterViewModel(0)
        viewModel.increment()
        assertThat(viewModel.count.value).isEqualTo(1)
    }

    @Test
    fun `1에서 decrement 하면 0이 된다`() {
        val viewModel = CounterViewModel(1)
        viewModel.decrement()
        assertThat(viewModel.count.value).isEqualTo(0)
    }

    @Test
    fun `0에서 decrement 하면 내려가지않고 0이 된다`() {
        val viewModel = CounterViewModel(0)
        viewModel.decrement()

        assertThat(viewModel.count.value).isEqualTo(0)
    }
}