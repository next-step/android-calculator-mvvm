package camp.nextstep.edu.calculator.data.db

import androidx.room.TypeConverter
import camp.nextstep.edu.calculator.data.db.entity.ResultEntity
import com.google.gson.Gson


class ListConverter {

    @TypeConverter
    fun listToJson(value: List<ResultEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<ResultEntity>::class.java).toList()
}
