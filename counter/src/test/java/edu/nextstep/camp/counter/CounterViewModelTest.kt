package edu.nextstep.camp.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {

    lateinit var viewModel: CounterViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `현재 값이 0일 경우 값을 증가시키면 1이 된다`() {
        //when
        viewModel.increment()
        val actual = viewModel.count.value

        //then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `현재 값이 2일 경우 값을 감소시키면 1이 된다`() {
        //given
        viewModel.increment()
        viewModel.increment()

        //when
        viewModel.decrement()
        val actual = viewModel.count.value

        //then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `감소하고자 하는 값이 음수가 될 경우 변경되지 않는다`() {
        //when
        val original = viewModel.count.value
        viewModel.decrement()
        val actual = viewModel.count.value

        //then
        assertThat(actual).isEqualTo(original)
    }

    @Test
    fun `감소하고자 하는 값이 음수가 되면 변경되지 않고 메세지를 업데이트 한다`() {
        //given
        val ERROR_MESSAGE = "0 이하로 내릴 수 없습니다."

        //when
        viewModel.decrement()
        val actual = viewModel.toastMessage.value

        //then
        assertThat(actual).isEqualTo(ERROR_MESSAGE)
    }
}