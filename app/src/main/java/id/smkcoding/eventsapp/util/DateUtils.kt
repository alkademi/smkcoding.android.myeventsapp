package id.smkcoding.eventsapp.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

fun convertStringDate(date: String, format: String): String {

    val sdf: SimpleDateFormat
    val dateFormat: SimpleDateFormat
    val convertedDate: Date

    sdf = SimpleDateFormat(format)
    dateFormat = SimpleDateFormat(SERVER_DATE_FORMAT)

    try {
        convertedDate = dateFormat.parse(date)
        return sdf.format(convertedDate)
    } catch (e: ParseException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
        return ""
    }

}