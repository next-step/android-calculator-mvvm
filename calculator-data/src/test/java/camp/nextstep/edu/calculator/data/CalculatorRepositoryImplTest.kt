package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import camp.nextstep.edu.calculator.data.local.CalculatorDatabase
import camp.nextstep.edu.calculator.data.repository.CalculatorRepositoryImpl
import camp.nextstep.edu.calculator.domain.Memory
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CalculatorRepositoryImplTest {

    private lateinit var calculatorRepository: CalculatorRepository

    @Before
    fun init() {
        val context: Context = ApplicationProvider.getApplicationContext()
        calculatorRepository =
            CalculatorRepositoryImpl(CalculatorDatabase.getDatabase(context).calculatorDao())
    }

    @Test
    fun `계산 결과 저장하고 가져오기`() = runTest {
        //given
        val memory = Memory("1 + 2", 3)

        // when
        calculatorRepository.saveMemory(memory.expression, memory.result)
        val actual = calculatorRepository.findMemories()

        // then
        assertThat(actual).contains(memory)
    }
}
