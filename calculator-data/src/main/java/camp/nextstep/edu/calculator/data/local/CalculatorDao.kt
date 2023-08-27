/**
 * @author Daewon on 27,August,2023
 *
 */

package camp.nextstep.edu.calculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import camp.nextstep.edu.calculator.data.entity.MemoryEntity

@Dao
interface CalculatorDao {
    @Insert
    suspend fun saveMemory(memory: MemoryEntity)

    @Query("SELECT * FROM memory")
    suspend fun findMemories(): List<MemoryEntity>
}
