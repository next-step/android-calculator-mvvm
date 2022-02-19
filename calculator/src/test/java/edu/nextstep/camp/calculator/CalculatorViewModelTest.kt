package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `입력된 피연산자가 없을때 0 버튼을 누르면 0이 보여야 한다`() {
        // when
        viewModel.addToExpression(0)

        // then
        val expected = "0"
        var expression = viewModel.expression.getOrAwaitValue().toString()
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `수식이 0 + 일때 1 버튼을 누르면 0 + 1이 보여야 한다`() {
        // given
        viewModel.addToExpression(0)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.addToExpression(1)

        // then
        val expected = "0 + 1"
        var expression = viewModel.expression.getOrAwaitValue().toString()
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `수식이 8 일때 9 버튼을 누르면 89가 보여야 한다`() {
        //given
        viewModel.addToExpression(8)

        // when
        viewModel.addToExpression(9)

        // then
        val expected = "89"
        var expression = viewModel.expression.getOrAwaitValue().toString()
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `수식이 8 + 일때 지우기 버튼을 누르면 8이 보여야 한다`() {
        // given
        viewModel.addToExpression(8)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.removeLast()

        val expected = "8"
        var expression = viewModel.expression.getOrAwaitValue().toString()
        assertThat(expression).isEqualTo(expected)
    }

    @Test
    fun `수식이 아무것도 입력이 없을때 = 버튼을 누르면 표현식 오류 이벤트가 발생한다`() {
        // when
        viewModel.calculate()

        val expected = viewModel.expressionError.getOrAwaitValue()
        assertThat(expected.consume()).isNotNull()
    }

    @Test
    fun `수식이 1 + 2 일때 = 버튼을 누르면 3의 결과가 보여야 한다`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)

        // when
        viewModel.calculate()

        // then
        val expected = "3"
        var expression = viewModel.expression.getOrAwaitValue().toString()
        assertThat(expression).isEqualTo(expected)
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