package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

internal class CounterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CounterViewModel

    @Before
    fun setUp() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `count 가 0일 때 upCount()를 3번 호출하면 count가 3이 된다`() {

        for (i in 1..3 step 1) {
            viewModel.upCount()
        }

        Truth.assertThat(viewModel.count.getOrAwaitValue().number).isEqualTo(3)
    }

    @Test
    fun `count 가 5일 때 downCount()를 3번 호출하면 count가 2가 된다`() {
        for (i in 1..5 step 1) {
            viewModel.upCount()
        }

        viewModel.downCount()
        viewModel.downCount()
        viewModel.downCount()

        Truth.assertThat(viewModel.count.getOrAwaitValue().number).isEqualTo(2)
    }

    @Test
    fun `count 가 음수가 되면 countIsZeroEvent 가 호출된다`() {
        viewModel.downCount()

        Truth.assertThat(viewModel.countIsZeroEvent.getOrAwaitValue()).isInstanceOf(Unit::class.java)
    }
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
