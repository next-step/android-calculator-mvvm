package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

internal class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `숫자 1이 입력되면 1이 보여아 한다`() {
        // when
        viewModel.addToExpression(1)

        // then
        val expected = "1"
        assertThat(viewModel.expression.getOrAwaitValue().toString()).isEqualTo(expected)
    }

    @Test
    fun `수식 2 + 3이 입력되면 2 + 3이 보여아 한다`() {
        // when
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(3)

        // then
        val expected = "2 + 3"
        assertThat(viewModel.expression.getOrAwaitValue().toString()).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 2 + 3 일 때 지우기 버튼을 누르면 2 + 이 된다`() {
        // given
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(3)

        // when
        viewModel.removeLast()

        // then
        val expected = "2 +"
        assertThat(viewModel.expression.getOrAwaitValue().toString()).isEqualTo(expected)
    }

    @Test
    fun `입력된 수식이 2 + 3 × 5 일 때 = 버튼을 누르면 25 이 보여야 한다`() {
        // given
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Multiply)
        viewModel.addToExpression(5)

        // when
        viewModel.calculate()

        // then
        val expected = "25"
        assertThat(viewModel.expression.getOrAwaitValue().toString()).isEqualTo(expected)
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