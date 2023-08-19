package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ResultExpressionEntityDbTest {

    private lateinit var calculatorDao: CalculatorDao
    private lateinit var db: CalculatorDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, CalculatorDatabase::class.java)
            .build()
        calculatorDao = db.getDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertResultExpressionGetAllListLastItem() {
        val resultExpressionEntity = ResultExpressionEntity(expression = "44 + 3", result = "47")

        calculatorDao.insert(resultExpressionEntity)
        val resultList = calculatorDao.getAll()
        val actual = resultList.last()

        assertThat(actual, equalTo(resultExpressionEntity))
    }
}
