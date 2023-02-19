package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.RecordRepository
import camp.nextstep.edu.calculator.domain.model.Record
import camp.nextstep.edu.calculator.util.getOrAwaitValue
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var recordRepository: RecordRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        recordRepository = mockk(relaxed = true)
        coEvery { recordRepository.getRecord() } returns listOf(
            Record("1 + 2", 3),
            Record("2 + 3", 5)
        )
        viewModel = CalculatorViewModel(recordRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `1을 입력하면 text에 1이 출력해야 한다`() {
        viewModel.addToExpression(1)
        assertEquals("1", viewModel.text.getOrAwaitValue())
    }

    @Test
    fun `1 + 2 를 입력하면 text에 1 + 2가 출력해야 한다`() {
        with(viewModel) {
            addToExpression(1)
            addToExpression(Operator.Plus)
            addToExpression(2)
        }
        assertEquals("1 + 2", viewModel.text.getOrAwaitValue())
    }

    @Test
    fun `저장된 record값을 읽어와서 text에 출력해야 한다`() {
        val expected = "1 + 2\n = 3\n2 + 3\n = 5"
        runBlocking {
            viewModel.getRecords()
        }
        assertEquals(expected, viewModel.text.getOrAwaitValue())
        coVerify {
            recordRepository.getRecord()
        }

    }

    @Test
    fun `가장 최근에 추가한 연산자, 피연산자를 삭제해야 한다`() {
        with(viewModel) {
            addToExpression(1)
            addToExpression(Operator.Plus)
            addToExpression(2)
            removeLast()
        }
        assertEquals("1 +", viewModel.text.getOrAwaitValue())
    }

    @Test
    fun `연산 결과를 text에 출력해야 한다`() {
        with(viewModel) {
            addToExpression(1)
            addToExpression(Operator.Plus)
            addToExpression(2)
        }
        viewModel.calculate()
        assertEquals("3", viewModel.text.getOrAwaitValue())
    }
}