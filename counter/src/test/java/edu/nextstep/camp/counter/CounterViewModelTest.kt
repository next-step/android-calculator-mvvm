package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class CounterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var counterViewModel: CounterViewModel

    @Before
    fun setup() {
        counterViewModel = CounterViewModel()
    }

    @Test
    fun `up 버튼 클릭시 count 값이 하나 증가한다`() {
        //when
        counterViewModel.countUp()

        //then
        val actual = counterViewModel.count.getOrAwaitValue()
        Truth.assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `up 버튼 클릭 후 down 버튼 클릭시 count 값은 0이다`() {
        //give
        counterViewModel.countUp()

        //when
        counterViewModel.countDown()

        //then
        val actual = counterViewModel.count.getOrAwaitValue()
        Truth.assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `count 가 0인 상태에서 down 버튼 클릭시 count 값은 0이다`() {
        //when
        counterViewModel.countDown()

        //then
        val actual = counterViewModel.count.getOrAwaitValue()
        Truth.assertThat(actual).isEqualTo(0)
    }
}
