package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CounterViewModel

    @Before
    fun setUp() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `counter 기본 초기값은 0이어야 한다`() {
        // then
        Truth.assertThat(viewModel.counter.getOrAwaitValue()).isEqualTo(0)
    }

    @Test
    fun `counter 값이 0일때 Up 버튼을 한번 누르면 counter 값이 1이 되어야 한다`() {
        // when
        viewModel.up()


        // then
        Truth.assertThat(viewModel.counter.getOrAwaitValue()).isEqualTo(1)
    }

    @Test
    fun `counter 값이 1일때 Down 버튼을 한번 누르면 counter 값이 1이 되어야 한다`() {
        //given
        viewModel = CounterViewModel(1)

        // when
        viewModel.down()

        // then
        Truth.assertThat(viewModel.counter.getOrAwaitValue()).isEqualTo(0)
    }

    @Test
    fun `counter 값이 0일때 Up 버튼을 5번 누르면 counter 값이 5가 되어야 한다`() {
        // when
        (0 until 5).forEach { _ ->
            viewModel.up()
        }

        // then
        Truth.assertThat(viewModel.counter.getOrAwaitValue()).isEqualTo(5)
    }

    @Test
    fun `counter 값이 5일때 Down 버튼을 5번 누르면 counter 값이 0이 되어야 한다`() {
        //given
        viewModel = CounterViewModel(5)

        // when
        (0 until 5).forEach { _ ->
            viewModel.down()
        }

        // then
        Truth.assertThat(viewModel.counter.getOrAwaitValue()).isEqualTo(0)
    }

    @Test
    fun `counter 값이 0일때 Down 버튼을 누르면 counter 값은 그대로 0이 되어야 한다`() {
        //given
        viewModel = CounterViewModel(0)

        // when
        viewModel.down()

        // then
        Truth.assertThat(viewModel.counter.getOrAwaitValue()).isEqualTo(0)
    }

    @Test
    fun `counter 값이 0일때 Down 버튼을 누르면 showErrorMessage에 이벤트 값이 전달되어야 한다`() {
        //given
        viewModel = CounterViewModel(0)

        // when
        viewModel.down()

        // then
        Truth.assertThat(viewModel.showErrorMessage.getOrAwaitValue()).isEqualTo(Unit)
    }
}