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
    fun `숫자를 1 증가시킬 수 있다`() {
        // when
        viewModel.increment()

        // then
        val actual = viewModel.counter.getOrAwaitValue()
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `숫자를 1 감소시킬 수 있다`() {
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
    fun `0 일 때 숫자를 감소시킬 수 없다`() {
        // when
        viewModel.decrement()

        // then
        val actual = viewModel.event.getOrAwaitValue().consume()
        assertThat(actual).isInstanceOf(CounterViewModel.ViewEvent.FailDecrement::class.java)
    }

}