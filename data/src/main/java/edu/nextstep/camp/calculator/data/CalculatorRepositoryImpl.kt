package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.CalculationRecord
import edu.nextstep.camp.calculator.domain.CalculatorRepository

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 31..
 */

internal class CalculatorRepositoryImpl(private val calculationRecordsDao: CalculationRecordDao) :
    CalculatorRepository {

    private var calculationRecordList: MutableList<CalculationRecord> = mutableListOf()

    private suspend fun getCalculationRecords(): List<CalculationRecord> {
        return calculationRecordsDao.getAll().map { it.mapToDomain() }
    }

    private suspend fun insert(calculationRecord: CalculationRecord) {
        return calculationRecordsDao.insert(CalculationRecordEntity.of(calculationRecord))
    }

    override suspend fun storeCalculationMemory(calculationRecord: CalculationRecord) {
        calculationRecordList.add(calculationRecord)

        insert(calculationRecord)
    }

    override suspend fun loadCalculationRecords() {
        calculationRecordList = getCalculationRecords().toMutableList()
    }

    override fun getCalculationRecordList(): MutableList<CalculationRecord> {
        return calculationRecordList
    }

}