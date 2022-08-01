package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var counterViewModel: CounterViewModel

    @Test
    fun `count가 2일 때 up 버튼을 클릭하면 count가 3이 된다`() {
        // given : count가 2일 때
        val inputValue = 2
        counterViewModel = CounterViewModel(inputValue)

        // when : up 버튼을 클릭하면
        counterViewModel.increaseCount()

        // then : count가 3이 된다
        val expectedValue = 3
        val actualValue = counterViewModel.count.getOrAwaitValue()
        assertThat(actualValue).isEqualTo(expectedValue)
    }
    
    @Test
    fun `count가 3일 때 down을 클릭하면 count가 2가 된다`() {
        // given : count가 3일 때
        val inputValue = 3
        counterViewModel = CounterViewModel(inputValue)

        // when : down을 클릭하면
        counterViewModel.decreaseCount()

        // then : count가 2가 된다
        val expectedValue = 2
        val actualValue = counterViewModel.count.getOrAwaitValue()
        assertThat(actualValue).isEqualTo(expectedValue)
    }

    @Test
    fun `count가 0일 때 down을 클릭하면 count는 0을 유지한다`() {
        // given : count가 0일 때
        val startValue = 0
        counterViewModel = CounterViewModel(startValue)

        // when : down을 클릭하면
        counterViewModel.decreaseCount()

        // then : count는 0을 유지한다
        val expectedValue = 0
        val actualValue = counterViewModel.count.getOrAwaitValue()
        assertThat(actualValue).isEqualTo(expectedValue)
    }

    @Test
    fun `count가 0일 때 down 클릭하면 0미만으로 내릴 수 없기에 event 전달`() {
        // given : count가 0일 때
        val startValue = 0
        counterViewModel = CounterViewModel(startValue)

        // when : down 클릭하면
        counterViewModel.decreaseCount()

        // then : 0미만으로 내릴 수 없기에 event 전달
        val expectedValue = CounterEventData.CAN_NOT_DECREASE_DOWN_ZERO
        val actualValue = counterViewModel.countEventDataLiveData.getOrAwaitValue().consume()
        assertThat(actualValue).isEqualTo(expectedValue)
    }

}