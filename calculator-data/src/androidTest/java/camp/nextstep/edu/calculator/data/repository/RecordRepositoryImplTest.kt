package camp.nextstep.edu.calculator.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import camp.nextstep.edu.calculator.data.db.RecordDatabase
import camp.nextstep.edu.calculator.domain.RecordRepository
import camp.nextstep.edu.calculator.domain.model.Record
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecordRepositoryImplTest {
    private lateinit var recordRepository: RecordRepository

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val recordDatabase = Room
            .inMemoryDatabaseBuilder(context, RecordDatabase::class.java)
            .build()
        recordRepository = RecordRepositoryImpl(
            recordDatabase,
        )

    }

    @Test
    fun 계산_결과_저장하고_가져오기() {
        // given
        val record = Record("1 + 2", 3)
        // when
        runBlocking {
            recordRepository.insertRecord(record)
            val actual = recordRepository.getRecord()
            //than
            assertTrue(actual.contains(record))
        }


    }
}