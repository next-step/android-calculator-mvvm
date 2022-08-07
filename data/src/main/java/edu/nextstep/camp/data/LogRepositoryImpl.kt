package edu.nextstep.camp.data

import edu.nextstep.camp.domain.LogRepository
import edu.nextstep.camp.domain.LogVO
import javax.inject.Inject

internal class LogRepositoryImpl @Inject constructor(private val dao: LogDao): LogRepository {
    override suspend fun insertLog(log: LogVO) = dao.insert(LogEntityMapper.toEntity(log))

    override suspend fun getLogs(): List<LogVO> {
        val entityList = dao.getAll()
        return entityList.map {
            LogEntityMapper.toVO(it)
        }
    }
}