package camp.nextstep.edu.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

internal class CounterViewModelTest {

    private val viewModel = CounterViewModel()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `0일때_UP버튼을_누르면_1`() {
        // given: 0

        // when: Up 버튼을 누르면
        viewModel.numberPlusOne()

        // then: 1이 된다
        val actual = viewModel.number.getOrAwaitValue()
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `0일때_UP_DOWN버튼을_누르면_0`() {
        // given: 0

        // when: Up Down 버튼을 누르면
        viewModel.numberPlusOne()
        viewModel.numberMinusOne()

        // then: 0이 된다
        val actual = viewModel.number.getOrAwaitValue()
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `0일때_DOWN버튼을_누르면_0`() {
        // given: 0

        // when: Down 버튼을 누르면
        viewModel.numberMinusOne()

        // then: 0이 유지된다.
        val actual = viewModel.number.getOrAwaitValue()
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `0일때_UP_UP버튼을_누르면_2`() {
        // given: 0

        // when: Up Up 버튼을 누르면
        viewModel.numberPlusOne()
        viewModel.numberPlusOne()

        // then: 2가 된다.
        val actual = viewModel.number.getOrAwaitValue()
        assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `0일때_UP_UP_DOWN버튼을_누르면_1`() {
        // given: 0

        // when: Up Up Down 버튼을 누르면
        viewModel.numberPlusOne()
        viewModel.numberPlusOne()
        viewModel.numberMinusOne()

        // then: 1가 된다.
        val actual = viewModel.number.getOrAwaitValue()
        assertThat(actual).isEqualTo(1)
    }
}