package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.domain.counter.Counter
import edu.nextstep.camp.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("NonAsciiCharacters")
class CounterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var counterViewModel: CounterViewModel

    @Before
    fun setup() {
        counterViewModel = CounterViewModel(Counter())
    }

    @Test
    fun `최초에 카운트 값으로 0을 보여줘야 한다`() {
        // then
        val actual = counterViewModel.counterUiState.getOrAwaitValue().count
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `현재 카운트 값이 0인 경우 UP 버튼을 클릭하면 숫자 1이 보여져야 한다`() {
        // when
        counterViewModel.countUp()

        // then
        val actual = counterViewModel.counterUiState.getOrAwaitValue().count
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `현재 카운트 값이 0인 경우 DOWN 버튼을 클릭하면 0 이하로 내릴 수 없다는 에러 메시지가 보여야 한다`() {
        // when
        counterViewModel.countDown()

        // then
        val actual = counterViewModel.counterUiState.getOrAwaitValue().errorMessage
        assertThat(actual).isEqualTo(UiText.StringResource(R.string.negative_count_not_supported))
    }

    @Test
    fun `현재 카운트 값이 0인 경우 DOWN 버튼을 클릭하면 숫자 0이 보여져야 한다`() {
        // when
        counterViewModel.countDown()

        // then
        val actual = counterViewModel.counterUiState.getOrAwaitValue().count
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `현재 카운트 값이 1인 경우 DOWN 버튼을 클릭하면 숫자 0이 보여져야 한다`() {
        // given
        counterViewModel.countUp()

        // when
        counterViewModel.countDown()

        // then
        val actual = counterViewModel.counterUiState.getOrAwaitValue().count
        assertThat(actual).isEqualTo(0)
    }
}