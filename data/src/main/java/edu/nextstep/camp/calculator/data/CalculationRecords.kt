package edu.nextstep.camp.calculator.data

import io.realm.RealmList
import io.realm.RealmObject

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 31..
 */
open class CalculationRecords(
    val calculationRecordList: RealmList<CalculationRecord> = RealmList()
) : RealmObject() {
    fun addCalculationRecord(expression: String, result: Int) {
        calculationRecordList.add(CalculationRecord(expression, result))
    }
}