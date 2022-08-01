package edu.nextstep.camp.calculator.data

import android.content.Context
import io.realm.Realm

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 31..
 */
object CalculatorRepository {

    var calculationRecords: CalculationRecords = CalculationRecords()
        private set
    private lateinit var mRealm: Realm

    fun init(context: Context) {
        Realm.init(context)
        mRealm = Realm.getDefaultInstance()
        mRealm.where(CalculationRecords::class.java)
            .findFirst()?.let { it ->
                it.calculationRecordList.forEach { item ->
                    val expression = item.expression ?: return@forEach
                    val result = item.result ?: return@forEach
                    calculationRecords.addCalculationRecord(expression, result)
                }
            }
    }

    fun storeCalculationMemory(expression: String, result: Int) {
        calculationRecords.addCalculationRecord(expression, result)

        mRealm.executeTransactionAsync { realm ->
            realm.deleteAll()
            realm.insert(calculationRecords)
        }
    }

}