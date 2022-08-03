package edu.nextstep.camp.calculator.domain

/**
 * Created by link.js on 2022. 08. 04..
 */
interface HistoryRepository {
    suspend fun setHistories(list: List<History>)
    suspend fun getHistories(): List<History>
}