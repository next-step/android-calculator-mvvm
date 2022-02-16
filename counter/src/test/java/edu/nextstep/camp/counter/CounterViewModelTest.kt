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
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `count 값이 0일때 up 버튼을 누르면 count 값이 1 증가 한다`() {
        // when
        viewModel.countUp()
        // then
        val expected = 1
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(expected)

    }

    @Test
    fun `count 값이 1일때 down 버튼을 누르면 count 값이 0으로 감소 된다` () {
        // given
        viewModel.setCount(1)

        // when
        viewModel.countDown()

        // then
        val expected = 0
        assertThat(viewModel.count.getOrAwaitValue()).isEqualTo(expected)
    }


    /* Copyright 2019 Google LLC.
   SPDX-License-Identifier: Apache-2.0 */
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
}