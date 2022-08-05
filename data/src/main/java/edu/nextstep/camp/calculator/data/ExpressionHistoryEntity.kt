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
internal data class ExpressionHistoryEntity(
    val rawExpression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)

internal fun ExpressionHistory.toEntity() =
    ExpressionHistoryEntity(rawExpression, result)

internal fun ExpressionHistoryEntity.toExpressionHistoryItem() =
    ExpressionHistory(rawExpression, result)

@Dao
internal abstract class ExpressionHistoryDao {
    @Query("SELECT * FROM expressionhistoryentity")
    abstract suspend fun getAll(): List<ExpressionHistoryEntity>

    @Insert
    abstract suspend fun insert(entities: List<ExpressionHistoryEntity>)

    @Query("DELETE FROM expressionhistoryentity")
    abstract suspend fun deleteAll()

    @Transaction
    open suspend fun setAll(entities: List<ExpressionHistoryEntity>) {
        deleteAll()
        insert(entities)
    }
}

@Database(entities = [ExpressionHistoryEntity::class], version = 2)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun expressionHistoryDao(): ExpressionHistoryDao

    companion object {
        private const val NAME = "APP_DATABASE"

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}