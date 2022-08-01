package edu.nextstep.camp.data

import edu.nextstep.camp.data.LogEntity

interface LogRepository {
    fun insertLog(log: LogEntity)
    fun getLogs(): List<LogEntity>
    fun getLastLog(): LogEntity
}