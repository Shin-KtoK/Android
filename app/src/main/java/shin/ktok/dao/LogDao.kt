package shin.ktok.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import shin.ktok.entity.Log

@Dao
interface LogDao {

    @Query("SELECT * from log_table ORDER BY id DESC")
    fun getAllLogs(): LiveData<List<Log>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Log)

    @Query("DELETE FROM log_table")
    suspend fun deleteAll()

}