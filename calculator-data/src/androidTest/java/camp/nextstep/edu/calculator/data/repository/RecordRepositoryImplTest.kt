package camp.nextstep.edu.calculator.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import camp.nextstep.edu.calculator.data.database.RecordDatabase
import camp.nextstep.edu.calculator.domain.model.Expression
import camp.nextstep.edu.calculator.domain.model.Record
import camp.nextstep.edu.calculator.domain.repository.RecordRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class RecordRepositoryImplTest {
    private lateinit var recordRepository: RecordRepository

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val recordDatabase = Room
            .inMemoryDatabaseBuilder(context, RecordDatabase::class.java)
            .build()
        recordRepository = RecordRepositoryImpl(
            recordDatabase.recordDao()
        )
    }

    @Test
    fun 계산_결과를_저장하고_가져온다() {
        //given
        val record = Record(Expression("1 + 2".split(" ")), 3)
        var actual: List<Record>

        //when
        runBlocking {
            recordRepository.saveRecord(record)
            actual = recordRepository.loadRecords()
        }

        //then
        assertTrue(actual.contains(record))
    }
}
