package utils

import java.text.DateFormat
import java.util.Date

fun dateToTimeStamp(date: Date): String {
     val dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
    return dateTimeFormat.format(date.time)
}