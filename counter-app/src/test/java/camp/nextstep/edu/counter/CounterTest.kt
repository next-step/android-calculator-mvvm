package camp.nextstep.edu.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class CounterTest {
    private val viewModel = CounterViewModel()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `count 2에서, UP 버튼을 누르면, count가 3이 된다`() {
        // given :
        viewModel.setCount(2)
        // when :
        viewModel.upCount()
        // than :
        val actualExpression = viewModel.count.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(3)
    }

    @Test
    fun `count 2에서 DOWN 버튼을 누르면 count가 1이 된다`() {
        // given :
        viewModel.setCount(2)
        // when :
        viewModel.downCount()
        // than :
        val actualExpression = viewModel.count.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(1)
    }

    @Test
    fun `count 0에서 DOWN 버튼을 누르면 count가 0이 된다`() {
        // given :
        viewModel.setCount(0)
        // when :
        viewModel.downCount()
        // than :
        val actualExpression = viewModel.count.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(0)
    }
}
