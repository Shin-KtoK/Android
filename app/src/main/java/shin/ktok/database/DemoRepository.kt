package shin.ktok.database

import android.app.Application
import androidx.lifecycle.LiveData
import shin.ktok.dao.LogDao
import shin.ktok.entity.Log
import shin.ktok.utility.DateTool
import kotlinx.coroutines.CoroutineScope


class DemoRepository(private val app: Application, private val scope: CoroutineScope) {

    private val logDao: LogDao by lazy {
        DemoRoomDatabase.getDatabase(app, scope).logDao()
    }

    val allLogs: LiveData<List<Log>> = logDao.getAllLogs()

    suspend fun insert(insertString: String) {
        val log = Log(id = 0, dataTime = DateTool.getUnixTime(), logLevel = 1, logText = insertString)
        logDao.insert(log)
    }

    suspend fun deleteAll() {
        logDao.deleteAll()
    }
}