package com.dispy.acnhdemo.model.bean

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/04
 * tpps89305@hotmail.com
 */
data class CommonItem(
    val fileName: String,
    val name: String,
    val iconURI: String,
    val tags: ArrayList<String>
)