package edu.nextstep.camp.calculator.data

import android.content.Context
import io.realm.Realm

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 31..
 */
object CalculatorRepository {

    val calculationRecord: CalculationRecord = CalculationRecord()

    fun init(context: Context) {
        Realm.init(context)
    }

    fun storeCalculationMemory(expression: String, result: Int) {
        val mRealm = Realm.getDefaultInstance()
        mRealm.executeTransactionAsync { realm ->
            realm.insertOrUpdate(calculationRecord)
        }

        calculationRecord.addCalculationRecord(expression, result)
    }

}
