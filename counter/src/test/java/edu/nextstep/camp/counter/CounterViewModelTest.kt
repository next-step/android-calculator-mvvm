package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.domain.counter.Counter
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `ViewModel은 기본적으로 EMPTY를 반환한다`() {
        // given
        val viewModel = CounterViewModel()
        val expected = CounterState.EMPTY

        // when
        // then
        val actual = viewModel.onViewState.getOrAwaitValue().consume() as CounterState.EMPTY
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Up 이벤트를 전달받으면 카운터는 +1 을 한다`() {
        // given
        val viewModel = CounterViewModel()
        val expected = 1

        // when
        viewModel.onEvent(CounterEvent.Up)

        // then
        val actual: CounterState.Counted = viewModel.onViewState.getOrAwaitValue().consume() as CounterState.Counted
        assertThat(actual.counteredNumber).isEqualTo(expected)
    }

    @Test
    fun `Down 이벤트를 전달받으면 카운터는 -1 을 한다`() {
        // given
        val viewModel = CounterViewModel(Counter(2))
        val expected = 1

        // when
        viewModel.onEvent(CounterEvent.Down)

        // then
        val actual: CounterState.Counted = viewModel.onViewState.getOrAwaitValue().consume() as CounterState.Counted
        assertThat(actual.counteredNumber).isEqualTo(expected)
    }

    @Test
    fun `0일때 Down 이벤트를 전달받으면 카운터 State는 ZeroDownError를 반환한다`() {
        // given
        val viewModel = CounterViewModel(Counter(0))
        val expected = CounterState.ZeroDownError

        // when
        viewModel.onEvent(CounterEvent.Down)

        // then
        val actual = viewModel.onViewState.getOrAwaitValue().consume() as CounterState.ZeroDownError
        assertThat(actual).isEqualTo(expected)
    }
}