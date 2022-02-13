package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.*
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
    fun `Counter 의 기본값은 0 이어야 한다`() {
        // then
        val expected = 0
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(expected)
    }

    @Test
    fun `입력된 값이 0 일 때 증가 버튼을 누르면 1 이 보여야 한다`() {
        // when
        viewModel.up()

        // then
        val expected = 1
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(expected)
    }

    @Test
    fun `입력된 값이 1 일 때 감소 버튼을 누르면 0 이 보여야 한다`() {
        // given
        viewModel.up()

        // when
        viewModel.down()

        // then
        val expected = 0
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(expected)
    }

    @Test
    fun `입력된 값이 0 일 때 감소 버튼을 누르면 0 이 보여야 한다`() {
        // when
        viewModel.down()

        // then
        val expected = 0
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(expected)
    }
}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}