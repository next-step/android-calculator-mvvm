package edu.nextstep.camp.calculator.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import edu.nextstep.camp.calculator.domain.model.RecordStatement

class Converters {
    @TypeConverter
    fun listToString(value: List<RecordStatement>?): String = Gson().toJson(value)

    @TypeConverter
    fun stringToList(value: String): List<RecordStatement> =
        Gson().fromJson(value, Array<RecordStatement>::class.java).toList()
}
