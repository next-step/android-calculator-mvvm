package edu.nextstep.camp.calculator.data

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.data.model.Statement
import edu.nextstep.camp.calculator.domain.model.RecordStatement
import org.junit.Test
import java.util.UUID

class MapperTest {
    @Test
    fun `Statement를 map() 메서드 호출시 RecordStatement 객체로 변경된다`() {
        // GIVEN
        val statement = Statement(
            uuid = UUID.randomUUID(),
            expression = "1 + 1",
            calculateResult = "2"
        )
        // WHEN
        val recordStatement = statement.map()

        // THEN
        assertThat(recordStatement).isInstanceOf(RecordStatement::class.java)
    }
}
