package edu.nextstep.camp.data

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.model.Memories
import edu.nextstep.camp.calculator.domain.model.Memory
import edu.nextstep.camp.data.database.CalculatorDao
import edu.nextstep.camp.data.entity.MemoryEntity
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executors

class CalculatorDefaultRepositoryTest {

    private lateinit var calculatorDao: CalculatorDao
    private lateinit var calculatorRepository: CalculatorRepository

    @Before
    fun setUp() {
        val executors = Executors.newSingleThreadExecutor()

        calculatorDao = mockk(relaxed = true, relaxUnitFun = true)
        calculatorRepository = CalculatorDefaultRepository(calculatorDao, executors)
    }

    @Test
    fun `단일_Memory_저장_후_가져오기`() {
        // given
        val memoryEntityList = mutableListOf<MemoryEntity>()

        every { calculatorDao.addMemory(capture(memoryEntityList)) } answers { nothing }
        every { calculatorDao.getMemoryList() } answers { memoryEntityList }

        //  when
        val memories = Memories(
            listOf(
                Memory(expression = "3 + 5", result = 8)
            )
        )

        memories.items.forEach { calculatorRepository.addMemory(it) }

        // then
        val actual = calculatorRepository.getMemories()

        assertThat(actual).isEqualTo(memories)
    }

    @Test
    fun `복수_Memory_저장_후_가져오기`() {
        // given
        val memoryEntityList = mutableListOf<MemoryEntity>()

        every { calculatorDao.addMemory(capture(memoryEntityList)) } answers { nothing }
        every { calculatorDao.getMemoryList() } answers { memoryEntityList }

        //  when
        val memories = Memories(
            listOf(
                Memory(expression = "3 + 5", result = 8),
                Memory(expression = "10 - 5", result = 5),
                Memory(expression = "2 * 8", result = 16),
                Memory(expression = "20 / 4", result = 5)
            )
        )

        memories.items.forEach { calculatorRepository.addMemory(it) }

        // then
        val actual = calculatorRepository.getMemories()

        assertThat(actual).isEqualTo(memories)
    }
}