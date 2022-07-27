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
    fun `up button을 누르면 count가 올라간다`() {
        //given
        val original = viewModel.count.value

        //when
        viewModel.increment()
        val actual = viewModel.count.value

        //then
        assertThat(actual).isEqualTo(original?.plus(1))
    }

    @Test
    fun `down button을 누르면 count가 내려간다`() {
        //given
        viewModel.increment()
        viewModel.increment()
        val original = viewModel.count.value

        //when
        viewModel.decrement()
        val actual = viewModel.count.value

        //then
        assertThat(actual).isEqualTo(original?.minus(1))
    }

    @Test
    fun `down button을 눌렀을 때, count가 음수가 되면, 내려가지 않는다`() {
        //when
        val original = viewModel.count.value
        viewModel.decrement()
        val actual = viewModel.count.value

        //then
        assertThat(actual).isEqualTo(original)
    }

    @Test
    fun `down button을 눌렀을 때, count가 음수가 되면, 메세지를 업데이트 한다`() {
        //given
        val ERROR_MESSAGE = "0 이하로 내릴 수 없습니다."

        //when
        viewModel.decrement()
        val actual = viewModel.toastMessage.value

        //then
        assertThat(actual).isEqualTo(ERROR_MESSAGE)
    }
}