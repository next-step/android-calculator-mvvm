package edu.nextstep.camp.data

class LogRepositoryImpl(private val dao: LogDao): LogRepository {
    override suspend fun insertLog(log: LogEntity) = dao.insert(log)

    override suspend fun getLogs(): List<LogEntity> = dao.getAll()
}