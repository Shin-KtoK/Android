package shin.ktok.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "log_table")
data class Log(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long,

    @ColumnInfo(name = "dateTime")
    var dataTime:Long,

    @ColumnInfo(name = "logLevel")
    var logLevel:Int,

    @ColumnInfo(name = "logText")
    var logText:String

)
