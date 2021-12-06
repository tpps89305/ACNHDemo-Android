package com.dispy.acnhdemo.model

import java.text.SimpleDateFormat
import java.util.*

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/08/29
 * tpps89305@hotmail.com
 */
class DateHandler {

    companion object {
        fun getCurrentMonth(): Int {
            val sdf = SimpleDateFormat("MM", Locale.TAIWAN)
            return sdf.format(Date()).toInt()
        }

        fun getCurrentHour(): Int {
            val sdf = SimpleDateFormat("HH", Locale.TAIWAN)
            return sdf.format(Date()).toInt()
        }

        fun getCurrentMinute(): Int {
            val sdf = SimpleDateFormat("mm", Locale.TAIWAN)
            return sdf.format(Date()).toInt()
        }

        fun getToday(): String {
            val sdf = SimpleDateFormat("d/M", Locale.TAIWAN)
            return sdf.format(Date()).toString()
        }
    }
}