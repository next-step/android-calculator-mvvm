package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CounterViewModel

    @Before
    fun setUp() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `count가 0일 때 증가하면 1이 된다`() {
        // GIVEN
        (viewModel.count as MutableLiveData<Int>).value = 0

        // WHEN
        viewModel.upCount()

        // THEN
        assertThat(viewModel.count.value).isEqualTo(1)
    }

    @Test
    fun `count가 1일 때 감소하면 0이 된다`() {
        // GIVEN
        (viewModel.count as MutableLiveData<Int>).value = 1

        // WHEN
        viewModel.downCount()

        // THEN
        assertThat(viewModel.count.value).isEqualTo(0)
    }

    @Test
    fun `count가 0일 때 감소하면 그대로 0이 된다`() {
        // GIVEN
        (viewModel.count as MutableLiveData<Int>).value = 0

        // WHEN
        viewModel.downCount()

        // THEN
        assertThat(viewModel.count.value).isEqualTo(0)
    }
}