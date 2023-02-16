package camp.nextstep.edu.calculator.local.converter

import androidx.room.TypeConverter
import camp.nextstep.edu.calculator.local.entity.CalculatorResultEntity
import com.google.gson.Gson

class ExpressionListTypeConverter {
    @TypeConverter
    fun listToJson(value: List<CalculatorResultEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<CalculatorResultEntity>::class.java).toList()

}