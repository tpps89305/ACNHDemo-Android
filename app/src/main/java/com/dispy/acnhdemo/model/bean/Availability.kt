package com.dispy.acnhdemo.model.bean

import com.google.gson.annotations.SerializedName

data class Availability (
    @SerializedName("month-northern")
    val monthNorthern: String,

    @SerializedName("month-southern")
    val monthSouthern: String,

    val time: String,
    val isAllDay: Boolean,
    val isAllYear: Boolean,
    val location: String,
    val rarity: String,

    @SerializedName("month-array-northern")
    val monthArrayNorthern: List<Int>,

    @SerializedName("month-array-southern")
    val monthArraySouthern: List<Int>,

    @SerializedName("time-array")
    val timeArray: List<Int>
)