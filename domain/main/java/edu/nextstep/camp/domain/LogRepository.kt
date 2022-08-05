package edu.nextstep.camp.domain

interface LogRepository {
    suspend fun insertLog(log: LogEntity)
    suspend fun getLogs(): List<LogEntity>
}