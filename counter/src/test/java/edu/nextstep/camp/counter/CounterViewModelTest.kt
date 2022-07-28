package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.counter.CounterEvent.*
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModel: CounterViewModel

    @Test
    fun `count값이 2일때 증가 요청시 count값이 1 증가 한 값인 3이 된다`() {
        // given count 값이 2 일때
        val startValue = 2
        viewModel = CounterViewModel(startValue)

        // when 증가 요청시
        viewModel.increaseCount()

        // then count 값은 3이 된다.
        val expected = 3
        val actual = viewModel.count.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `count값이 3일때 감소 요청시 count값이 1 감소 한 값인 2이 된다`() {
        // given count 값이 3 일때
        val startValue = 3
        viewModel = CounterViewModel(startValue)

        // when 감소 요청시
        viewModel.decreaseCount()

        // then count 값은 2이 된다.
        val expected = 2
        val actual = viewModel.count.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `count값이 0일때 감소 요청시 count값은 0을 유지한다`() {
        // given count 값이 0 일때
        val startValue = 0
        viewModel = CounterViewModel(startValue)

        // when 감소 요청시
        viewModel.decreaseCount()

        // then count 값은 0을 유지한다.
        val expected = 0
        val actual = viewModel.count.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `count값이 0일때 감소 요청시 0미만으로 내릴 수 없음 event를 전달 한다`() {
        // given count 값이 0 일때
        val startValue = 0
        viewModel = CounterViewModel(startValue)

        // when 감소 요청시
        viewModel.decreaseCount()

        // then 0미만으로 내릴 수 없음 event를 전달 한다
        val expected = CAN_NOT_DECREASE_COUNT_UNDER_0
        val actual = viewModel.countEvent.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

}