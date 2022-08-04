package edu.nextstep.camp.data

interface LogRepository {
    suspend fun insertLog(log: LogEntity)
    suspend fun getLogs(): List<LogEntity>
}