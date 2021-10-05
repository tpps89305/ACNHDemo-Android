package com.dispy.acnhdemo.model.bean

import androidx.annotation.DrawableRes

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/10/02
 * tpps89305@hotmail.com
 */
class AvailableNowInfo(
    @DrawableRes val iconRes: Int,
    var amount: String,
    val title: String
)