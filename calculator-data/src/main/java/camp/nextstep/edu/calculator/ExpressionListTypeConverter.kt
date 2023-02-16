package camp.nextstep.edu.calculator

import androidx.room.TypeConverter
import com.google.gson.Gson

class ExpressionListTypeConverter {
    @TypeConverter
    fun listToJson(value: List<CalculatorResultData>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<CalculatorResultData>::class.java).toList()

}