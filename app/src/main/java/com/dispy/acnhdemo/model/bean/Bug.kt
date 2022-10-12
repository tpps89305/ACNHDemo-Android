package com.dispy.acnhdemo.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/12
 * tpps89305@hotmail.com
 */

typealias BugsMap = HashMap<String, Bug>

data class Bug (
    val id: Long,

    @SerializedName("file-name")
    val fileName: String,

    val name: Name,
    val availability: Availability,
    val price: Long,

    @SerializedName("price-flick")
    val priceFlick: Long,

    @SerializedName("catch-phrase")
    val catchPhrase: String,

    @SerializedName("museum-phrase")
    val museumPhrase: String,

    @SerializedName("image_uri")
    val imageURI: String,

    @SerializedName("icon_uri")
    val iconURI: String,

    @SerializedName("alt-catch-phrase")
    val altCatchPhrase: List<String>? = null
): Serializable