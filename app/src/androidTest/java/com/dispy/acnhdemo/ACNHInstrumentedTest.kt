package com.dispy.acnhdemo


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.dispy.acnhdemo.view.custom.TimeScaleView


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ACNHInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.dispy.acnhdemo", appContext.packageName)
    }

    @Test
    fun testTimeScaleView() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val timeScaleView = TimeScaleView(appContext)

        val testAvaTime = ArrayList<Int>()
        testAvaTime.add(0)
        testAvaTime.add(1)
        testAvaTime.add(2)
        testAvaTime.add(3)

        timeScaleView.setAvailableTimes(testAvaTime)

        val expectResult = ArrayList<IntArray>()
        expectResult.add(intArrayOf(0, 4))

        assertArrayEquals(expectResult[0].toTypedArray(), timeScaleView.getAvailableTimes()[0].toTypedArray())
    }
}