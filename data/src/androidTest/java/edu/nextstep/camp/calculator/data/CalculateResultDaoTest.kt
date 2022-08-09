package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.data.dao.CalculateResultDao
import edu.nextstep.camp.calculator.data.model.CalculateResultEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculateResultDaoTest {
    private lateinit var database: CalculatorDatabase
    private lateinit var dao: CalculateResultDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = CalculatorDatabase.getDatabase(context)
        dao = database.calculateResultDao()
    }

    @Test
    fun `수식과_결과를_디비에_삽입하면_디비에_삽입한_값을_정상적으로_불러온다`() {
        val entity = CalculateResultEntity(expression = "1 + 1", result = 2)
        runBlocking {
            dao.insertCalculateResult(entity)
            val results: List<CalculateResultEntity> = dao.getCalculateResults().first() ?: listOf()
            assertThat(results[0].result).isEqualTo(2)
        }
    }

    @After
    fun end() {
        database.close()
    }
}
