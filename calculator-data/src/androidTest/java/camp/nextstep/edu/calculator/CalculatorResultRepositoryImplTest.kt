package camp.nextstep.edu.calculator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import camp.nextstep.edu.calculator.domain.model.CalculatorResultData
import camp.nextstep.edu.calculator.domain.repository.CalculatorResultRepository
import camp.nextstep.edu.calculator.local.di.InjectDatabase
import camp.nextstep.edu.calculator.local.impl.CalculatorResultRepositoryImpl
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class CalculatorResultRepositoryImplTest {
    private lateinit var repository: CalculatorResultRepository


    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val dao = InjectDatabase.getDB(context)?.calculatorResultDao()!!
        repository = CalculatorResultRepositoryImpl(
            dao = dao,
            Executors.newSingleThreadExecutor()
        )
    }

    @Test
    fun `DB에_저장하고_데이터_확인하기`() {
        // when
        val data = CalculatorResultData("1 + 2", 3)
        repository.saveResult(data)

        //then
        val actual = repository.getAllResult()?.get(0)
        Truth.assertThat(actual).isEqualTo(data)
    }

}