package shin.ktok.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import shin.ktok.database.DemoRepository
import shin.ktok.utility.DateTool
import shin.ktok.utility.DateTool.Companion.FORMAT_TIMESTAMP
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException

class MainViewModel(private val app: Application): AndroidViewModel(app) {

    private val repository: DemoRepository by lazy {
        DemoRepository(app, viewModelScope)
    }

    val allLog : LiveData<List<shin.ktok.entity.Log>> = repository.allLogs

    fun deleteAll( ) = viewModelScope.launch {
        repository.deleteAll()
    }


    fun insert(insertString: String) = viewModelScope.launch {
        repository.insert(insertString)
    }

    fun saveCSV(insertString: String) {
        try {
            val file = File(app.filesDir, "log.csv")
            Log.i("filesDir", file.path)

            if (!file.exists()) {
                file.createNewFile()
            }

            var id = 1
            file.forEachLine {
                val csvId = it.substringBefore(",").toIntOrNull()
                if (csvId != null && csvId >= id) {
                    id = csvId.toInt() + 1
                }
            }

            val dataTime = DateTool.getToday(FORMAT_TIMESTAMP)
            val logLevel = 1

            val builder = buildString {
                append(id)
                append(",")
                append(dataTime)
                append(",")
                append(logLevel)
                append(",")
                append(insertString)
                appendln()
            }

            file.appendText(builder)

        } catch (e: FileNotFoundException) {
            println(e)
        }
    }

}