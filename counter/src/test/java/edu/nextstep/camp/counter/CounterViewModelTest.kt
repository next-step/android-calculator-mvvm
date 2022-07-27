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
    fun `UP button click and increase one`() {
        // given
        viewModel = CounterViewModel(1)

        // when
        viewModel.incrementCount()

        // then
        val actualCountData = viewModel.count.value
        assertThat(actualCountData).isEqualTo(2)
    }

    @Test
    fun `Down button click and decrease one`() {
        // given
        viewModel = CounterViewModel(3)

        // when
        viewModel.decrementCount()

        // then
        val actualCountData = viewModel.count.value
        assertThat(actualCountData).isEqualTo(2)
    }

    @Test
    fun `when zero down click and execute error message event`() {
        // given
        viewModel = CounterViewModel()

        // when
        viewModel.decrementCount()

        // then
        val actualCountData = viewModel.count.value
        assertThat(actualCountData).isEqualTo(0)
    }
}