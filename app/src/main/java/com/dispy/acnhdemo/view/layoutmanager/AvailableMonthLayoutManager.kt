package com.dispy.acnhdemo.view.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/08/29
 * tpps89305@hotmail.com
 */
class AvailableMonthLayoutManager(context: Context, columns: Int): GridLayoutManager(context, columns) {

    override fun canScrollHorizontally(): Boolean {
        return false
    }

    override fun canScrollVertically(): Boolean {
        return false
    }

}