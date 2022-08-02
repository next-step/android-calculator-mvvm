package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CalculationResultStorageTest {
    private lateinit var storage: CalculationResultStorage

    @BeforeEach
    fun setUp() {
        storage = CalculationResultStorage()
    }

    @Test
    fun `계산기록 저장소에 추가된 계산기록이 없을 때 계산 결과를 가져오면 빈 list를 반환한다`() {
        // when 추가된 계산기록이 없을 때 계산기록을 요청하면
        val resultList = storage.getResultsAsList()

        // then 빈 list를 반환한다.
        assertThat(resultList.isEmpty()).isEqualTo(true)
    }

    @Test
    fun `계산기록 저장소에 계산기록을 추가 하면 추가된 계산 기록을 받아 올 수 있다`() {
        // when 계산기록 저장소에 계산기록을 추가 하면
        val expectedExpression = Expression(listOf("1", Operator.Plus, "1"))
        val expected = CalculationResult(expectedExpression, 2)
        storage += expected

        // then 추가된 계산 기록을 받아 올 수 있다.
        val resultList = storage.getResultsAsList()
        val actual = resultList.first()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `계산기록 저장소에 계산기록을 2개 이상 추가 하면, 추가된 순서대로 저장된 계산 기록을 받아 올 수 있다`() {
        // when 계산기록 저장소에 계산기록을 2개 이상 추가 하면
        val firstExpectedExpression = Expression(listOf("1", Operator.Plus, "1"))
        val firstExpected = CalculationResult(firstExpectedExpression, 2)
        storage += firstExpected

        val secondExpectedExpression = Expression(listOf("2", Operator.Plus, "2"))
        val secondExpected = CalculationResult(secondExpectedExpression, 4)
        storage += secondExpected

        // then 추가된 순서대로 저장된 계산 기록을 받아 올 수 있다.
        val resultList = storage.getResultsAsList()
        assertThat(resultList[0]).isEqualTo(firstExpected)
        assertThat(resultList[1]).isEqualTo(secondExpected)
    }
}