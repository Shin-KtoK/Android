package shin.ktok.utility

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateTool {
    companion object{

        const val FORMAT_TIMESTAMP = "yyyyMMddHHmmss"

        fun getUnixTime(): Long {
            return Date().time
        }

        fun getToday(format: String): String {
            return SimpleDateFormat(format, Locale.getDefault()).format(Date())
        }

        fun fromTimestamp(value: Long): String {
            val date = Date(value)
            val format = SimpleDateFormat(FORMAT_TIMESTAMP, Locale.getDefault())
            return format.format(date)
        }

    }


}