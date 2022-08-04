package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.CalculationRecord

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 04..
 */
fun CalculationRecordEntity.mapToDomain(): CalculationRecord = CalculationRecord(
    this.expression,
    this.result
)