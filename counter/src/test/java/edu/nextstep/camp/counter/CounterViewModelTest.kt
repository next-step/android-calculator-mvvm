package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.*
import edu.nextstep.camp.counter.utils.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {
    private lateinit var viewModel: CounterViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `현재 카운트가 0일 때, 증가 함수를 호출하면, count 가 1증가하여 1이 된다`() {
        // when :
        viewModel.increaseCount()
        // then :
        val actualCount = viewModel.count.getOrAwaitValue()
        assertThat(actualCount).isEqualTo(1)
    }

    @Test
    fun `현재 카운트가 1일 때, 감소 함수를 호출하면, count 가 -1 감소하여 0이 된다`() {
        // given :
        viewModel = CounterViewModel(initialCount = 1)
        // when :
        viewModel.decreaseCount()
        // then :
        val actualCount = viewModel.count.getOrAwaitValue()
        assertThat(actualCount).isEqualTo(0)
    }

    @Test
    fun `현재 카운트가 0일 때, 감소 함수를 호출하면, 0이하로 내릴 수 없는 이벤트가 발생한다`() {
        // given :
        // when :
        viewModel.decreaseCount()
        // then :
        val actualEvent = viewModel.lessThenZeroEvent.getOrAwaitValue().peek()
        assertThat(actualEvent).isTrue()
    }
}
