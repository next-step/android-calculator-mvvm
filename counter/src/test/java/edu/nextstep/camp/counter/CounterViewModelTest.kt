package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var counterViewModel: CounterViewModel

    @Test
    fun increment_counter_should_increase_count() {
        //when
        counterViewModel = CounterViewModel()

        //given
        counterViewModel.incrementCounter()

        //then
        Truth.assertThat(counterViewModel.count.value).isEqualTo(1)
    }

    @Test
    fun when_count_is_zero_decrement_counter_should_not_decrease_count() {
        //when
        counterViewModel = CounterViewModel()

        //given
        counterViewModel.decrementCounter()

        //then
        Truth.assertThat(counterViewModel.count.value).isEqualTo(0)
    }

    @Test
    fun when_count_bigger_than_zero_decrement_counter_should_decrease_count() {
        //when
        counterViewModel = CounterViewModel()
        counterViewModel.incrementCounter()
        counterViewModel.incrementCounter()

        //given
        counterViewModel.decrementCounter()

        //then
        Truth.assertThat(counterViewModel.count.value).isEqualTo(1)
    }
}
