package com.dispy.acnhdemo

import com.dispy.acnhdemo.model.DateHandler
import com.dispy.acnhdemo.view.custom.TimeScaleView
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
}