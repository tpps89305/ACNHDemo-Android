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
    }
}