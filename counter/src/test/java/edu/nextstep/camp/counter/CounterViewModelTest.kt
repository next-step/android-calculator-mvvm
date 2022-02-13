package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class CounterViewModelTest {

    private lateinit var viewModel: CounterViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `counterEvent의 기본 값은 0이어야 한다`() {
        // given
        // when
        // then
        val expected = 0
        assertThat(viewModel.countEvent.getOrAwaitValue()).isEqualTo(expected)
    }

    @Test
    internal fun `주어진 값이 0일 때 up을 한 번 호출하면 1이 되어야 한다`() {
        // given
        // when
        viewModel.up()

        // then
        val expected = 1
        assertThat(viewModel.countEvent.getOrAwaitValue()).isEqualTo(expected)
    }

    @Test
    fun `주어진 값이 0일 때는 down이 호출되도 계속 0이어야 한다`() {
        // given
        // when
        // then
        val expected = 0
        assertThat(viewModel.countEvent.getOrAwaitValue()).isEqualTo(expected)
    }

    @Test
    fun `주어진 값이 1일 때 down이 호출되면 0이 되어야 한다`() {
        // given
        viewModel = CounterViewModel(1)

        // when
        viewModel.down()

        // then
        val expected = 0
        assertThat(viewModel.countEvent.getOrAwaitValue()).isEqualTo(expected)
    }
}