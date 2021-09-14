package com.dispy.acnhdemo.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/14
 * tpps89305@hotmail.com
 */
data class Fossil (
    @SerializedName("file-name")
    val fileName: String,

    val name: Name,
    val price: Long,

    @SerializedName("museum-phrase")
    val museumPhrase: String,

    @SerializedName("image_uri")
    val imageURI: String,

    @SerializedName("part-of")
    val partOf: String
): Serializable