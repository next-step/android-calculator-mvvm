package edu.nextstep.camp.calculator.domain


/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 03..
 */
interface CalculatorRepository {
    val calculationRecordList: List<Any>

    suspend fun storeCalculationMemory(expression: String, result: Int)
    suspend fun loadCalculationRecords()
}