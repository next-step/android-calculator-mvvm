package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var counterViewModel: CounterViewModel

    @Before
    fun setUp() {
        counterViewModel = CounterViewModel(initialValue = 0)
    }

    @Test
    fun `UP 버튼을 클릭하면 숫자가 1 증가해야 한다`() {
        // given
        //when
        counterViewModel.up()

        // then
        assertThat(counterViewModel.count.value).isEqualTo(1)
    }

    @Test
    fun `숫자가 1일때 UP 버튼을 클릭하면 숫자가 1 증가한 2다`() {
        // given
        counterViewModel.up()

        // when
        counterViewModel.up()

        // then
        assertThat(counterViewModel.count.value).isEqualTo(2)
    }

    @Test
    fun `숫자가 1일때 DOWN 버튼을 클릭하면 숫자가 1 감소한 0이다`() {
        // given
        counterViewModel.up()

        //when
        counterViewModel.down()

        // then
        assertThat(counterViewModel.count.value).isEqualTo(0)
    }

    @Test
    fun `숫자가 0일때 DOWN 버튼을 클릭하면 숫자가 그대로 0이다`() {
        // given
        // when
        counterViewModel.down()

        // then
        assertThat(counterViewModel.count.value).isEqualTo(0)
    }
}
