package camp.nextstep.edu.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `upButton 클릭`() {
        // given :viewModel을 선언한다.
        val viewModel = CounterViewModel()

        // when : clickUpButton()를 호출한다.
        viewModel.clickUpButton()
        viewModel.clickUpButton()
        viewModel.clickUpButton()

        // then : 기존 수 보도 click 횟수 만큼 증가 한다.
        assertThat(viewModel.counter.value).isEqualTo(3)
    }

    @Test
    fun `카운터가 0일때 downButton 클릭`() {
        // given :viewModel을 선언한다.
        val viewModel = CounterViewModel()

        // when : clickUpButton()을 호출한다.
        viewModel.clickDownButton()

        // then : 카운터 값에 변화가 없다.
        assertThat(viewModel.counter.value).isEqualTo(0)
    }

    @Test
    fun `카운터가 1 이상일 때 downButton 클릭`() {
        // given :viewModel을 선언하고 upButton()호출을 통해 counter를 증가시킨다.
        // counter == 2
        val viewModel = CounterViewModel(2)

        // when : clickUpButton()을 호출한다.
        viewModel.clickDownButton()

        // then : 카운터 값이 하나 줄어든다.
        assertThat(viewModel.counter.value).isEqualTo(1)
    }
}
