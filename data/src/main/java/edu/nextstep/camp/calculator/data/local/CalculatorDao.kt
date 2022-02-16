package edu.nextstep.camp.calculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.nextstep.camp.calculator.data.model.Statement
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CalculatorDao {
    @Query("SELECT * FROM statement")
    fun getAll(): Flow<List<Statement>>

    @Insert
    fun insertStatement(statement: Statement)
}
