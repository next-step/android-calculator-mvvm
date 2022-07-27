package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CounterViewModel

    @Test
    fun increase() {
        // given
        viewModel = CounterViewModel()

        // when
        viewModel.increase()

        // then
        val actual = viewModel.liveNumber.getOrAwaitValue()
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun decrease() {
        // given
        viewModel = CounterViewModel(3)

        // when
        viewModel.decrease()

        // then
        val actual = viewModel.liveNumber.getOrAwaitValue()
        assertThat(actual).isEqualTo(2)
    }
}
