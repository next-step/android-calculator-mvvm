package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

internal class CounterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val viewModel: CounterViewModel = CounterViewModel()

    @Test
    fun `UP 버튼을 클릭하면 숫자가 1 증가해야 한다`() {
        // given
        val givenCount = viewModel.count.getOrAwaitValue()

        // when
        viewModel.increase()

        // then
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(givenCount + 1)
    }

    @Test
    fun `DOWN 버튼을 클릭하면 숫자가 1 감소해야 한다`() {
        // given
        val givenCount = viewModel.count.getOrAwaitValue()

        // when
        viewModel.decrease()

        // then
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(givenCount - 1)
    }
}
