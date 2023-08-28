package camp.nextstep.edu.counter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.counter.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CounterViewModel

    @Before
    fun setUp() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `뷰모델 초기화 이후 카운트 값은 0 이다`() {
        val count = viewModel.count.getOrAwaitValue()
        val expected = 0
        assertThat(count).isEqualTo(expected)
    }

    @Test
    fun `카운트업을 클릭하면 카운트가 1 증가한다`() {
        viewModel.onClickUp()

        val count = viewModel.count.getOrAwaitValue()
        val expected = 1
        assertThat(count).isEqualTo(expected)
    }

    @Test
    fun `카운트다운을 클릭하고 1 이상의 카운트인 경우 카운트가 1 감소한다`() {
        viewModel.onClickUp()
        viewModel.onClickDown()

        val count = viewModel.count.getOrAwaitValue()
        val expected = 0
        assertThat(count).isEqualTo(expected)
    }

    @Test
    fun `카운트다운을 클릭하고 음수가 되는 경우 카운트 값은 변하지 않는다`() {
        viewModel.onClickDown()
        val count = viewModel.count.getOrAwaitValue()
        val expected = 0
        assertThat(count).isEqualTo(expected)
    }
}