package data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.github.dodobest.data.AppDatabase
import com.github.dodobest.data.ResultRecordDao
import com.github.dodobest.data.ResultRecordEntity
import com.github.dodobest.data.util.TestUtil
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class AppDatabaseTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var resultRecordDao: ResultRecordDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        resultRecordDao = db.resultRecordDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun givenResultRecord_whenInsertIntoDB_thenSaveResultRecord() {
        // given : "2+2", "4"을 가진 ResultRecord가 있을때
        val expression = "2+2"
        val result = "4"
        val resultRecord: ResultRecordEntity = TestUtil.createEmptyResultRecord().copy(expression, result)

        // when : DB에 넣으면
        resultRecordDao.insertResultRecord(resultRecord)

        // then : "2+2", "4"을 가진 ResultRecord가 저장되어야 한다
        val byExpression = resultRecordDao.findResultRecordByExpression(expression)
        resultRecord.id = byExpression.id
        assertThat(byExpression).isEqualTo(resultRecord)
    }
}