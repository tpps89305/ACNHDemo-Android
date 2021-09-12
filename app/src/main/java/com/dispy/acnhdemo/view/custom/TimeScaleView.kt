package com.dispy.acnhdemo.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.dispy.acnhdemo.R

/**
 * Created by tpps8 on 2021/09/11
 *
 */
class TimeScaleView: View {

    constructor(context: Context): super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init(context, attrs)
    }

    private val bottomLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = resources.getDimension(R.dimen.paint_width)
        style = Paint.Style.STROKE
    }

    private val scaleLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = resources.getDimension(R.dimen.scale_line_1_width)
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.text_size_14)
    }

    private val valuePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = resources.getDimension(R.dimen.scale_line_3_length)
        color = ContextCompat.getColor(context, android.R.color.holo_red_light)
    }

    private val rect = Rect()

    private val startEndTimeArray = ArrayList<IntArray>()

    private fun init(context: Context, attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TimeScaleView,
            0, 0
        ).apply {
            try {
                // attr

            } finally {
                recycle()
            }
        }
    }

    fun setAvailableTimes(arrayTime: List<Int>) {
        startEndTimeArray.clear()

        var start = -1
        var end: Int

        for (i in arrayTime.indices) {
            if (start == -1) {
                start = arrayTime[i]
                continue
            }
            if (arrayTime[i] - arrayTime[i - 1] != 1) {
                end = arrayTime[i - 1] + 1
                startEndTimeArray.add(intArrayOf(start, end))
                start = arrayTime[i]
                continue
            }
            if (i == arrayTime.size - 1) {
                end = arrayTime[i] + 1
                startEndTimeArray.add(intArrayOf(start, end))
            }
        }
        invalidate()
        requestLayout()
    }

    fun getAvailableTimes(): ArrayList<IntArray> = startEndTimeArray

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val padding = resources.getDimension(R.dimen.padding)

        canvas?.apply {

            // Draw bottom line
            drawLine(padding, height - padding, width - padding, height - padding, bottomLinePaint)
            val bottomLineLength = width - padding * 2

            // Initial variable for draw
            val scaleLine1Length = resources.getDimension(R.dimen.scale_line_1_length)
            val scaleLine1Interval = bottomLineLength / 4

            // Draw value line
            val scaleValueLineInterval: Float = bottomLineLength / 24f
            for (each in startEndTimeArray.iterator()) {
                val xStartLine = padding + scaleValueLineInterval * each[0]
                val xEndLine = padding + scaleValueLineInterval * each[1]
                val yLine = height - padding - scaleLine1Length / 2
                drawLine(xStartLine, yLine, xEndLine, yLine, valuePaint)
            }

            // Draw scale line 1
            for (i in 0 until 5) {
                val xLine = padding + scaleLine1Interval * i
                val yStartLine = height - padding
                val yStopLine = height - padding - scaleLine1Length
                drawLine(xLine, yStartLine, xLine, yStopLine, scaleLinePaint)
            }

            // Draw scale line 2
            val scaleLine2Length = resources.getDimension(R.dimen.scale_line_2_length)
            val distanceBetweenScaleLine2AndBottom = resources.getDimension(R.dimen.distance_between_scale_line_2_and_bottom)
            for (i in 0 until 4) {
                val xLine = padding + scaleLine1Interval / 2 + scaleLine1Interval * i
                val yStartLine = height - padding - distanceBetweenScaleLine2AndBottom
                val yStopLine = height - padding - distanceBetweenScaleLine2AndBottom - scaleLine2Length
                drawLine(xLine, yStartLine, xLine, yStopLine, scaleLinePaint)
            }

            // Draw scale line 3
            val scaleLine3Length = resources.getDimension(R.dimen.scale_line_3_length)
            val scaleLine3Interval: Float = bottomLineLength / 24f
            val distanceBetweenScaleLine3AndBottom = resources.getDimension(R.dimen.distance_between_scale_line_3_and_bottom)
            for (i in 1 until 24) {
                if (i % 3 == 0) {
                    continue
                }
                val xLine = padding + scaleLine3Interval * i
                val yStartLine = height - padding - distanceBetweenScaleLine3AndBottom
                val yStopLine = height - padding - distanceBetweenScaleLine3AndBottom - scaleLine3Length
                drawLine(xLine, yStartLine, xLine, yStopLine, scaleLinePaint)
            }

            // Draw text
            for (i in 0..18 step 6) {
                val hourText = i.toString()
                textPaint.getTextBounds(hourText, 0, hourText.length, rect)
                val xText = padding + scaleLine3Interval * i - rect.width() / 2
                val yText = height - padding - scaleLine1Length - rect.height() / 2
                drawText(hourText, xText, yText, textPaint)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)

        val minh: Int = paddingBottom + paddingTop + suggestedMinimumHeight
        val h: Int = resolveSizeAndState(minh, heightMeasureSpec, 0)

        setMeasuredDimension(w, h)
    }

}