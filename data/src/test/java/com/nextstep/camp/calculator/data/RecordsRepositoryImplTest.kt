package com.nextstep.camp.calculator.data

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Record
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class RecordsRepositoryImplTest {

    private lateinit var recordDao: RecordDao
    private lateinit var recordsRepository: RecordsRepositoryImpl

    @BeforeEach
    fun setUp() {
        recordDao = mockk(relaxed = true)
        recordsRepository = RecordsRepositoryImpl(recordDao)
    }

    @Test
    fun `전체 레코드를 불러올 수 있다`() = runBlocking {
        // given
        val entity = RecordEntity(
            expression = "1 + 2",
            result = 3.0,
        )
        coEvery { recordDao.getAll() } returns listOf(entity)

        // when
        val records: List<Record> = recordsRepository.getAll()

        // then
        assertThat(records).containsExactly(Record(expression = "1 + 2", result = 3.0))
        coVerify { recordDao.getAll() }
    }

    @Test
    fun `하나의 레코드를 추가할 수 있다`() = runBlocking {
        // given
        val record = Record(expression = "1 + 2", result = 3.0)

        // when
        recordsRepository.insert(record)

        // then
        coVerify { recordDao.insert(RecordEntity(expression = "1 + 2", result = 3.0)) }
    }
}
