package com.dispy.acnhdemo.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/15
 * tpps89305@hotmail.com
 */
data class Art (
    val id: Long,

    @SerializedName("file-name")
    val fileName: String,

    val name: Name,
    val hasFake: Boolean,

    @SerializedName("buy-price")
    val buyPrice: Long,

    @SerializedName("sell-price")
    val sellPrice: Long,

    @SerializedName("image_uri")
    val imageURI: String,

    @SerializedName("museum-desc")
    val museumDesc: String
): Serializable