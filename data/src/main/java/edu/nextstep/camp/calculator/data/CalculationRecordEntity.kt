package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.CalculationRecord

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 04..
 */
@Entity
class CalculationRecordEntity(
    val expression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
) {
    companion object {
        fun of(calculationRecord: CalculationRecord): CalculationRecordEntity =
            CalculationRecordEntity(
                expression = calculationRecord.expression,
                result = calculationRecord.result
            )
    }
}