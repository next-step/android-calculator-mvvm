package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import camp.nextstep.edu.calculator.data.local.CalculatorDatabase
import camp.nextstep.edu.calculator.data.repository.DefaultCalculatorRepository
import camp.nextstep.edu.calculator.domain.Memory
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DefaultCalculatorRepositoryTest {

    private lateinit var calculatorRepository: CalculatorRepository

    @Before
    fun init() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val calculatorDatabase = Room.inMemoryDatabaseBuilder(context, CalculatorDatabase::class.java).build()
        calculatorRepository = DefaultCalculatorRepository(calculatorDatabase.calculatorDao())
    }

    @Test
    fun 계산_결과_저장하고_가져오기() = runTest {
        //given
        val memory = Memory("1 + 2", 3)

        // when
        calculatorRepository.saveMemory(memory.expression, memory.result)
        val actual = calculatorRepository.findMemories()

        // then
        assertThat(actual).contains(memory)
    }
}
