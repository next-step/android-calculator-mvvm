package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.domain.Operator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

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

class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `피연산자를 수식에 추가`() {
        // when
        viewModel.addTerm(1)

        // then
        assertEquals(viewModel.text.getOrAwaitValue(), "1")
    }

    @Test
    fun `연산자를 수식에 추가`() {
        // given
        viewModel.addTerm(1)
        assertEquals(viewModel.text.getOrAwaitValue(), "1")

        // when
        viewModel.addTerm(Operator.ADD)

        // then
        assertEquals(viewModel.text.getOrAwaitValue(), "1 +")
    }

    @Test
    fun `'1 + 2' 수식에서 마지막을 제거하면 '1 +'`() {
        // given
        viewModel.addTerm(1)
        viewModel.addTerm(Operator.ADD)
        viewModel.addTerm(2)
        assertEquals(viewModel.text.getOrAwaitValue(), "1 + 2")

        // when
        viewModel.removeTerm()

        // then
        assertEquals(viewModel.text.getOrAwaitValue(), "1 +")
    }

    @Test
    fun `'1 + 2' 수식을 계산하면 3`() {
        // given
        viewModel.addTerm(1)
        viewModel.addTerm(Operator.ADD)
        viewModel.addTerm(2)
        assertEquals(viewModel.text.getOrAwaitValue(), "1 + 2")

        // when
        viewModel.calculate()

        // then
        assertEquals(viewModel.text.getOrAwaitValue(), "3")
    }

    @Test
    fun `'1 +' 수식을 계산하면 에러`() {
        // given
        viewModel.addTerm(1)
        viewModel.addTerm(Operator.ADD)
        assertEquals(viewModel.text.getOrAwaitValue(), "1 +")

        // when
        viewModel.calculate()

        // then
        assertEquals(viewModel.exceptionMessage.getOrAwaitValue(), "완성되지 않은 수식입니다.")
    }

    @Test
    fun `0으로 나누면 에러`() {
        // given
        viewModel.addTerm(1)
        viewModel.addTerm(Operator.DIVIDE)
        viewModel.addTerm(0)
        assertEquals(viewModel.text.getOrAwaitValue(), "1 / 0")

        // when
        viewModel.calculate()

        // then
        assertEquals(viewModel.exceptionMessage.getOrAwaitValue(), "0으로 나눌 수 없습니다.")
    }

    @Test
    fun `Int의 범위를 넘어서면 에러`() {
        // given
        viewModel.addTerm(1)
        viewModel.addTerm(1)
        viewModel.addTerm(1)
        viewModel.addTerm(1)
        viewModel.addTerm(1)
        viewModel.addTerm(1)
        viewModel.addTerm(1)
        viewModel.addTerm(1)
        viewModel.addTerm(1)
        viewModel.addTerm(1)
        assertEquals(viewModel.text.getOrAwaitValue(), "1111111111")

        // when
        viewModel.addTerm(1)

        // then
        assertEquals(viewModel.exceptionMessage.getOrAwaitValue(), "숫자로 변환 불가능한 문자입니다.")
    }
}