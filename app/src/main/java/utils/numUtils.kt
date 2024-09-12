package utils

import kotlin.math.ln
import kotlin.math.pow

fun getFormatedNumber(count: Long): String {
    if (count < 1000) return "" + count
    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
    val totalNumber = count / 1000.0.pow(exp.toDouble())
    return String.format("%.1f %c", totalNumber, "kMGTPE"[exp - 1])
}