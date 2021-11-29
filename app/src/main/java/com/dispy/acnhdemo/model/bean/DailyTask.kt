package com.dispy.acnhdemo.model.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/11/28
 * tpps89305@hotmail.com
 */
@Entity
class DailyTask (
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "image_name") var imageName: String? = "",
    @ColumnInfo(name = "current_value") var currentValue: Int?,
    @ColumnInfo(name = "max_value") var maxValue: Int?
)