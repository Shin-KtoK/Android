package shin.ktok.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import shin.ktok.dao.LogDao
import shin.ktok.entity.Log
import shin.ktok.utility.DateTool
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Log::class], version = 1, exportSchema = false)
public abstract class DemoRoomDatabase:RoomDatabase() {

    abstract fun logDao(): LogDao

    private class LogDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.logDao())
                }
            }
        }

        suspend fun populateDatabase(logDao: LogDao) {

            val nowString:Long = DateTool.getUnixTime()

            val log = Log(id = 0,dataTime = nowString,logLevel = 1,logText = "DatabaseをOpenしました")
            logDao.insert(log)
        }
    }

    companion object{

        private var INSTANCE: DemoRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): DemoRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DemoRoomDatabase::class.java,
                    "memo_database"
                ).addCallback(
                    LogDatabaseCallback(
                        scope
                    )
                )
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }
}