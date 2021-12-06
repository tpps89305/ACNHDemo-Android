package com.dispy.acnhdemo

import com.dispy.acnhdemo.model.DateHandler
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ACNHUnitTest {
    @Test
    fun testGetDate() {
        assertEquals(9, DateHandler.getCurrentMonth())
    }

    @Test
    fun testGetTime() {
        assertEquals(22, DateHandler.getCurrentHour())
        assertEquals(56, DateHandler.getCurrentMinute())
    }

    @Test
    fun testGetToday() {
        assertEquals("7/12", DateHandler.getToday())
    }

}