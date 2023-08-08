package camp.nextstep.edu.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val viewModel = MainViewModel()

    @Test
    fun `downCount를 호출하여 0을 확인한다`() {
        viewModel.downCount()

        val actual = viewModel.count.value

        Truth.assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `downCount를 호출하여 SHOW_TAOST 이벤트를 확인한다`() {
        viewModel.downCount()

        Truth.assertThat(
            MainViewModel.EventType.SHOW_TOAST
        ).isEqualTo(viewModel.event.value?.consume())
    }

    @Test
    fun `upCount를 호출하여 1을 확인한다`() {
        viewModel.upCount()

        val actual = viewModel.count.value

        Truth.assertThat(actual).isEqualTo(1)
    }
}
