package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import edu.nextstep.camp.calculator.domain.ExpressionHistory

@Entity
data class ExpressionHistoryEntry(
    val rawExpression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)

fun ExpressionHistory.toEntry() =
    ExpressionHistoryEntry(rawExpression, result)

fun ExpressionHistoryEntry.toExpressionHistoryItem() =
    ExpressionHistory(rawExpression, result)

@Dao
abstract class ExpressionHistoryDao {
    @Query("SELECT * FROM expressionhistoryentry")
    abstract fun getAll(): List<ExpressionHistoryEntry>

    @Insert
    abstract fun insert(entities: List<ExpressionHistoryEntry>)

    @Query("DELETE FROM expressionhistoryentry")
    abstract fun deleteAll()

    @Transaction
    open fun setAll(entities: List<ExpressionHistoryEntry>) {
        deleteAll()
        insert(entities)
    }
}

@Database(entities = [ExpressionHistoryEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expressionHistoryDao(): ExpressionHistoryDao

    companion object {
        private const val NAME = "APP_DATABASE"

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NAME
            ).build()
        }
    }
}