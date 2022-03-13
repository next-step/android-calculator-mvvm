package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.model.Memory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var fakeRepository: FakeRepository


    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        val memory1 = Memory("1 + 2", "3")

        fakeRepository = FakeRepository(memory1)

        viewModel = CalculatorViewModel(
            calculator = Calculator(),
            historyRepository = fakeRepository
        )
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

    @Test
    fun `계산 히스토리 내역이 보이지 않을 때 히스토리 내역 버튼을 누르면 보여야 한다`() {
        // given

        // when
        viewModel.showAndHideHistoryList()

        // then
        val historyVisible = viewModel.memoryVisible.getOrAwaitValue()
        assertThat(historyVisible).isTrue()
    }

    @Test
    fun `수식을 계산을 하면 히스토리에 저장 되고 이전 계산 기록들을 가져올수 있어야 한다`() {
        // given
       viewModel = CalculatorViewModel(
           calculator = Calculator(),
           Expression(listOf("2 + 3")),
           historyRepository = fakeRepository
       )
        // when
        viewModel.calculate()

        // then
        val expected = listOf(
            Memory("1 + 2", "3"),
            Memory("2 + 3", "5")
        ).joinToString()
        val history = viewModel.expressionMemory.getOrAwaitValue().joinToString()
        assertThat(history).isEqualTo(expected)
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