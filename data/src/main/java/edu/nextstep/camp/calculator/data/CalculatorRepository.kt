package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Room

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 31..
 */

object CalculatorRepository {

    private lateinit var db: CalculatorDatabase

    var calculationRecords: CalculationRecords = CalculationRecords()
        private set

    fun init(context: Context) {
        db = Room.databaseBuilder(
            context,
            CalculatorDatabase::class.java, "calculation_records.db"
        ).build()
    }

    suspend fun getCalculationRecords(): List<CalculationRecords> {
        return db.calculationRecordsDao().getAll()
    }

    private suspend fun insert() {
        return db.calculationRecordsDao().insert(calculationRecords)
    }

    suspend fun remove() {
        db.calculationRecordsDao().delete(calculationRecords)
    }

    suspend fun storeCalculationMemory(expression: String, result: Int) {
        calculationRecords.addCalculationRecord(expression, result)

        insert()
    }

    suspend fun loadCalculationRecords() {
        getCalculationRecords().getOrNull(0)?.let {
            calculationRecords = it
        }
    }

}

/*
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


 */