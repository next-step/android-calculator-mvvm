package edu.nextstep.camp.domain

interface LogRepository {
    suspend fun insertLog(log: LogVO)
    suspend fun getLogs(): List<LogVO>
}