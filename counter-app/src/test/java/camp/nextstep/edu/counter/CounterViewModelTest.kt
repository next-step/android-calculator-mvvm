package camp.nextstep.edu.counter

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
	fun `CounterViewModel 생성 시, count 값은 0 이다`() {
		// then
		val count = counterViewModel.count.getOrAwaitValue()
		assertThat(count).isEqualTo(0)
	}

	@Test
	fun `countUp 호출 시, count 값이 1 증가한다`() {
		// when
		counterViewModel.countUp()

		// then
		val count = counterViewModel.count.getOrAwaitValue()
		assertThat(count).isEqualTo(1)
	}

	@Test
	fun `count 값이 1 이상일 때, countDown 호출 시, count 값이 1 감소하고 true 를 반환한다`() {
		// given
		counterViewModel.countUp()

		// when
		val result = counterViewModel.countDown()

		// then
		val count = counterViewModel.count.getOrAwaitValue()
		assertThat(count).isEqualTo(0)
		assertThat(result).isEqualTo(true)
	}

	@Test
	fun `count 값이 0 이하일 때, countDown 호출 시, false 를 반환한다`() {
		// when
		val result = counterViewModel.countDown()

		// then
		val count = counterViewModel.count.getOrAwaitValue()
		assertThat(count).isEqualTo(0)
		assertThat(result).isEqualTo(false)
	}
}