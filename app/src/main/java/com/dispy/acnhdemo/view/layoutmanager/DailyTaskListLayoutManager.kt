package com.dispy.acnhdemo.view.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/11/28
 * tpps89305@hotmail.com
 */
class DailyTaskListLayoutManager(context: Context): GridLayoutManager(context, 4) {

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
        lp.width = width / spanCount
        return true
    }

}