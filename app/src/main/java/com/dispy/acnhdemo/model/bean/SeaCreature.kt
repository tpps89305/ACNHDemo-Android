package com.dispy.acnhdemo.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/04
 * tpps89305@hotmail.com
 */

typealias SeaCreaturesMap = HashMap<String, SeaCreature>

data class SeaCreature (
    val id: Long,

    @SerializedName("file-name")
    val fileName: String,

    val name: Name,
    val availability: Availability,
    val speed: String,
    val shadow: String,
    val price: Long,

    @SerializedName("catch-phrase")
    val catchPhrase: String,

    @SerializedName("image_uri")
    val imageURI: String,

    @SerializedName("icon_uri")
    val iconURI: String,

    @SerializedName("museum-phrase")
    val museumPhrase: String
) : Serializable