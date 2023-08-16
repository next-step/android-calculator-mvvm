package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import camp.nextstep.edu.calculator.data.repository.DataInjector
import camp.nextstep.edu.calculator.domain.data.ResultExpression
import camp.nextstep.edu.calculator.domain.repository.ResultExpressionRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ResultExpressionRepositoryTest {

    private lateinit var resultExpressionRepository: ResultExpressionRepository

    @Before
    fun setUp() {

        val context: Context = ApplicationProvider.getApplicationContext()
        resultExpressionRepository = DataInjector.provideMemoryRepository(context)
    }

    @Test
    @Throws(Exception::class)
    fun `계산 결과 저장하고 가져온다`() {
        CoroutineScope(Dispatchers.IO).launch {
            val resultExpression = ResultExpression(expression = "44 + 3", result = "47")

            resultExpressionRepository.addResultExpression(resultExpression)
            val memoryList = resultExpressionRepository.getResultExpressionList()
            val actual = memoryList[memoryList.size - 1]

            assertThat(actual).isEqualTo(resultExpression)
        }
    }
}
