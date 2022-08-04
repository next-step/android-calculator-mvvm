package edu.nextstep.camp.counter

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CounterViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        // 1
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `초기값은 0이다`() = runTest {
        // given
        val viewModel = CounterViewModel()

        // then
        val actual = viewModel.count.first()
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `숫자를_증가시킨다`() = runTest {
        // given
        val viewModel = CounterViewModel()
        // when
        viewModel.increment()
        advanceUntilIdle()
        // then
        val actual = viewModel.count.first()
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `0_일때_숫자를_감소시킨면_0_이하로_내릴_수_없습니다_문구의_토스트가_보여진다`() = runTest {
        // given
        val viewModel = spyk<CounterViewModel>(recordPrivateCalls = true)
        val toastMessageSlot = slot<String>()
        every { viewModel["showToast"](capture(toastMessageSlot)) } returns Job()
        // when
        viewModel.decrement()
        advanceUntilIdle()
        // then
        val actual = toastMessageSlot.captured
        verify(exactly = 1) { viewModel["showToast"](actual) }
        assertThat(actual).isEqualTo("0 이하로 내릴 수 없습니다")
    }

    @Test
    fun `1_일때_숫자를_감소시킨면_0이_된다`() = runTest {
        // given
        val viewModel = CounterViewModel()
        viewModel.increment()
        // when
        viewModel.decrement()
        advanceUntilIdle()
        // then
        val actual = viewModel.count.first()
        assertThat(actual).isEqualTo(0)
    }
}
