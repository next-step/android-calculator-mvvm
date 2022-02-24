package edu.nextstep.camp.data.mapper

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.model.Memory
import edu.nextstep.camp.data.entity.MemoryEntity
import org.junit.Test

class MemoryEntityMapper {

    @Test
    fun `MemoryEntity의 확장함수 mapToDomain을 사용하면 도메인 Memory로 변경된다`() {
        // given
        val memoryEntity = MemoryEntity(
            expression = "3 + 5",
            result = 8
        )

        // when
        val actual = memoryEntity.mapToDomain()

        // then
        assertThat(actual).isEqualTo(Memory("3 + 5", 8))
    }
}