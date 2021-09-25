package com.dispy.acnhdemo.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/25
 * tpps89305@hotmail.com
 */

data class BGM (
    val id: Long,

    @SerializedName("file-name")
    val fileName: String,

    val hour: Long,
    val weather: String,

    @SerializedName("music_uri")
    val musicURI: String
): Serializable
