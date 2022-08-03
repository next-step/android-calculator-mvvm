package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Room
import edu.nextstep.camp.calculator.domain.CalculatorRepository

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 31..
 */

internal object CalculatorRepositoryImpl : CalculatorRepository, RepositorySetting {

    private lateinit var db: CalculatorDatabase

    private var _calculationRecordList: MutableList<CalculationRecord> = mutableListOf()

    override val calculationRecordList: List<CalculationRecord>
        get() = _calculationRecordList

    override fun init(context: Context) {
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

    override suspend fun storeCalculationMemory(expression: String, result: Int) {
        _calculationRecordList.add(CalculationRecord(expression, result))

        insert(CalculationRecord(expression, result))
    }

    override suspend fun loadCalculationRecords() {
        _calculationRecordList = getCalculationRecords().toMutableList()

    }

}