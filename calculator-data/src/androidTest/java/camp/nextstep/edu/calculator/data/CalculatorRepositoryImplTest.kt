package camp.nextstep.edu.calculator.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import camp.nextstep.edu.calculator.data.repository.CalculatorRepositoryImpl
import camp.nextstep.edu.calculator.data.room.CalculatorDatabase
import camp.nextstep.edu.calculator.domain.model.Record
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorRepositoryImplTest {
    private lateinit var calculatorRepository: CalculatorRepository

    @Before
    fun setUp() {
        val calculatorDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CalculatorDatabase::class.java
        ).build()

        calculatorRepository = CalculatorRepositoryImpl(
            dao = calculatorDatabase.calculatorDao()
        )
    }

    @Test
    fun `계산_결과_저장하고_가져오기`() {
        val record = Record(statement = "1 + 2", result = 3)

        runBlocking {
            calculatorRepository.insertRecord(record)
            val actual = calculatorRepository.getAllRecords().first()
            assertThat(actual).containsExactly(record)
        }
    }
}