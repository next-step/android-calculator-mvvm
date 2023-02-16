package camp.nextstep.edu.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModelTest: CounterViewModel

    @Before
    fun setUp() {
        viewModelTest = CounterViewModel()
    }

    @Test
    fun `숫자가 0인 상태에서 up 버튼을 클릭하면 숫자가 1 증가하여 1이 되어야 한다`() {
        // when
        viewModelTest.increaseNumber()

        // then
        val actual = viewModelTest.upDownUiState.getOrAwaitValue()
        assertEquals(1, actual)
    }

    @Test
    fun `숫자가 1인 상태에서 down 버튼을 클릭하면 숫자가 1 감소하여 0이 되어야 한다`() {
        // given
        viewModelTest.increaseNumber()

        // when
        viewModelTest.decreaseNumber()

        // then
        val actual = viewModelTest.upDownUiState.getOrAwaitValue()
        assertEquals(0, actual)
    }

    @Test
    fun `숫자가 0인 상태에서 down 버튼을 클릭하면 숫자 변화가 없어야 한다`() {
        // when
        viewModelTest.decreaseNumber()

        // then
        val actual = viewModelTest.upDownUiState.getOrAwaitValue()
        assertEquals(0, actual)
    }
}