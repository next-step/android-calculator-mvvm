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
        counterViewModel = CounterViewModel()
    }

    @Test
    fun `UP 을 실행하면 숫자가 1 증가해야 한다`() {
        // When
        counterViewModel.upCount()

        // Then
        assertThat(counterViewModel.count.getOrAwaitValue()).isEqualTo(1)
    }

    @Test
    fun `DOWN 을 실행하면 숫자가 1 감소해야 한다`() {
        // Given
        counterViewModel = CounterViewModel(3)

        // When
        counterViewModel.downCount()

        // Then
        assertThat(counterViewModel.count.getOrAwaitValue()).isEqualTo(2)
    }

    @Test
    fun `숫자는 음수가 될 수 없다`() {
        // When
        counterViewModel.downCount()

        // Then
        assertThat(counterViewModel.count.getOrAwaitValue()).isEqualTo(0)
    }

    @Test
    fun `0일 때 DOWN 을 실행하면 0 이하로 내릴 수 없습니다 토스트 메시지가 보여야 한다`() {
        // When
        counterViewModel.downCount()

        // Then
        assertThat(counterViewModel.showErrorToast.getOrAwaitValue()).isEqualTo(Unit)
    }
}