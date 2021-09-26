package com.dispy.acnhdemo.model.bean

import androidx.annotation.DrawableRes

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/26
 * tpps89305@hotmail.com
 */
data class CatalogItem (
    @DrawableRes val iconRes: Int,
    val title: String
)