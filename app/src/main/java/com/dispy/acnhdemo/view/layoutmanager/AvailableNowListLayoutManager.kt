package com.dispy.acnhdemo.view.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/10/05
 * tpps89305@hotmail.com
 */
class AvailableNowListLayoutManager(context: Context): LinearLayoutManager(context, HORIZONTAL, false) {

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
        // force height of viewHolder here, this will override layout_height from xml
        lp.width = width / 3
        return true
    }

}