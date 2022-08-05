package edu.nextstep.camp.data

import edu.nextstep.camp.domain.LogRepository

object Injector {
    fun provideLogRepository(dao: LogDao): LogRepository {
        return LogRepositoryImpl(dao)
    }
}