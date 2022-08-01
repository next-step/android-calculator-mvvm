package edu.nextstep.camp.calculator.data

import android.content.SharedPreferences
import androidx.core.content.edit
import edu.nextstep.camp.calculator.data.mapper.toData
import edu.nextstep.camp.calculator.data.mapper.toDomain
import edu.nextstep.camp.calculator.data.model.HistoryData
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.HistoryRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class HistoryRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : HistoryRepository {

    override suspend fun save(historyList: List<History>): Result<Unit> {
        return runCatching {
            sharedPreferences.edit {
                putString(KEY_HISTORY_LIST, Json.encodeToString(historyList.map { it.toData() }))
            }
        }
    }

    override suspend fun fetch(): Result<List<History>> {
        return runCatching {
            val rawList = sharedPreferences.getString(KEY_HISTORY_LIST, null) ?: return@runCatching emptyList()
            Json.decodeFromString<List<HistoryData>>(rawList).map { it.toDomain() }
        }
    }

    companion object {
        private const val KEY_HISTORY_LIST = "history_list"
    }
}
