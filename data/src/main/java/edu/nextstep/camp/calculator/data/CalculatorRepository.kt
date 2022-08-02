package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Room

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 31..
 */

object CalculatorRepository {

    private lateinit var db: CalculatorDatabase

    var calculationRecordList: MutableList<CalculationRecord> = mutableListOf()
        private set

    fun init(context: Context) {
        db = Room.databaseBuilder(
            context,
            CalculatorDatabase::class.java, "calculationRecord.db"
        ).build()
    }

    private suspend fun getCalculationRecords(): List<CalculationRecord> {
        return db.calculationRecordsDao().getAll()
    }

    private suspend fun insert(calculationRecord: CalculationRecord) {
        return db.calculationRecordsDao().insert(calculationRecord)
    }

    suspend fun storeCalculationMemory(expression: String, result: Int) {
        calculationRecordList.add(CalculationRecord(expression, result))

        insert(CalculationRecord(expression, result))
    }

    suspend fun loadCalculationRecords() {
        calculationRecordList = getCalculationRecords().toMutableList()

    }

}