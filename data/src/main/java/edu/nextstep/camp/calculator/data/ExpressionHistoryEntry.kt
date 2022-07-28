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
import edu.nextstep.camp.calculator.domain.ExpressionHistoryItem

@Entity
data class ExpressionHistoryEntry(
    val rawExpression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)

fun ExpressionHistoryItem.toEntry() =
    ExpressionHistoryEntry(rawExpression, result)

fun ExpressionHistoryEntry.toExpressionHistoryItem() =
    ExpressionHistoryItem(rawExpression, result)

@Dao
interface ExpressionHistoryDao {
    @Query("SELECT * FROM expressionhistoryentry")
    fun getAll(): List<ExpressionHistoryEntry>

    @Insert
    fun insert(entities: List<ExpressionHistoryEntry>)
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