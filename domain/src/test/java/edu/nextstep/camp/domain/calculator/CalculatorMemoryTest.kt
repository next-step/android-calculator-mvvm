package edu.nextstep.camp.domain.calculator

import com.google.common.truth.Truth.*
import org.junit.jupiter.api.Test

class CalculatorMemoryTest {

    @Test
    fun `빈 메모리에, 계산기록을 추가하면, 기록이 추가된다`() {
        // given :
        val calculatorMemory = CalculatorMemory()
        val expression = Expression(1, Operator.Plus, 2)
        val result = 3
        // when :
        calculatorMemory.addRecord(expression, result)
        // then :
        assertThat(calculatorMemory.records.size).isEqualTo(1)
    }

    @Test
    internal fun `비어있지 않은 메모리에, 계산기록을 추가하면, 맨 뒤에 추가한 계산 기록이 있다`() {
        // given :
        val oldExpression = Expression(1, Operator.Plus, 2)
        val calculatorMemory = CalculatorMemory()
        calculatorMemory.addRecord(oldExpression, 3)
        // when :
        val newExpression = Expression(4, Operator.Plus, 3)
        calculatorMemory.addRecord(newExpression, 7)
        // then :
        val expectedRecord = CalculatorMemory.Record(newExpression, 7)
        assertThat(calculatorMemory.records.last()).isEqualTo(expectedRecord)
    }
}
