package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import camp.nextstep.edu.calculator.data.repository.DataInjector
import camp.nextstep.edu.calculator.domain.data.Memory
import camp.nextstep.edu.calculator.domain.repository.MemoryRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MemoryRepositoryTest {

    private lateinit var memoryRepository: MemoryRepository

    @Before
    fun setUp() {

        val context: Context = ApplicationProvider.getApplicationContext()
        memoryRepository = DataInjector.provideMemoryRepository(context)
    }

    @Test
    @Throws(Exception::class)
    fun `계산 결과 저장하고 가져온다`() {
        CoroutineScope(Dispatchers.IO).launch {
            val memory = Memory(expression = "44 + 3", result = "47")

            memoryRepository.addMemory(memory)
            val memoryList = memoryRepository.getMemoryList()
            val actual = memoryList[memoryList.size - 1]

            assertThat(actual).isEqualTo(memory)
        }
    }
}
