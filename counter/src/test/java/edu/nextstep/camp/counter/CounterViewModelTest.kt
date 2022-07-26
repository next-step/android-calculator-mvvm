package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val viewModel = CounterViewModel()

    @Test
    fun increment() {
        // when
        viewModel.increment()

        // then
        val actual = viewModel.counter.getOrAwaitValue()
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun decrement() {
        // given 2
        viewModel.increment()
        viewModel.increment()

        // when
        viewModel.decrement()

        // then
        val actual = viewModel.counter.getOrAwaitValue()
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `decrement at zero is not allowed`() {
        // when
        viewModel.decrement()

        // then
        val actual = viewModel.event.getOrAwaitValue().consume()
        assertThat(actual).isInstanceOf(CounterViewModel.ViewEvent.FailDecrement::class.java)
    }

}