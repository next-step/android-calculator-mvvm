package camp.nextstep.edu.counter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.counter.domain.Counter
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val viewModel = MainViewModel()

    @Test
    fun `0에서 1 증가`() {
        viewModel.increase()
        val actual = viewModel.counter.value
        assertThat(actual).isEqualTo(Counter(1))
    }

    @Test
    fun `1에서 0 감소`() {
        viewModel.increase()
        viewModel.decrease()
        val actual = viewModel.counter.value
        assertThat(actual).isEqualTo(Counter(0))
    }
}
