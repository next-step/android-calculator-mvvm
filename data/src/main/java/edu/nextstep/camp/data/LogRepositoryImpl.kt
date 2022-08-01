package edu.nextstep.camp.data

class LogRepositoryImpl(private val db: LogDatabase): LogRepository {
    override fun insertLog(log: LogEntity) = db.logDao().insert(log)

    override fun getLogs(): List<LogEntity> = db.logDao().getAll()

    override fun getLastLog(): LogEntity = db.logDao().getLast()
}