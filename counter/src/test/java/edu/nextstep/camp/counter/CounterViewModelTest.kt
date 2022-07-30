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
    fun `UP 버튼을 클릭하면 숫자가 1 증가해야 한다`() {
        // given
        viewModel = CounterViewModel(1)
        // when
        viewModel.increment()
        // then
        val actual = viewModel.count.value
        assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `DOWN 버튼을 클릭하면 숫자가 1 감소해야 한다`() {
        // given
        viewModel = CounterViewModel(3)
        // when
        viewModel.decrement()
        // then
        val actual = viewModel.count.value
        assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `0일 때, DOWN 버튼을 클릭하면 오류 메시지 이벤트가 발생한다`() {
        viewModel = CounterViewModel()
        // when
        viewModel.decrement()
        // then
        val actual = viewModel.errorEvent.value
        assertThat(actual).isEqualTo(ErrorEvent.CalculatorError)
    }
}