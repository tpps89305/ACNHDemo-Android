package com.dispy.acnhdemo.model.bean

import com.google.gson.annotations.SerializedName

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json = Json(JsonConfiguration.Stable)
// val song = json.parse(Song.serializer(), jsonString)

typealias Songs = HashMap<String, Song>

data class Song (
    val id: Long,

    @SerializedName("file-name")
    val fileName: String,

    val name: Name,

    @SerializedName("buy-price")
    val buyPrice: Long? = null,

    @SerializedName("sell-price")
    val sellPrice: Long,

    val isOrderable: Boolean,

    @SerializedName("music_uri")
    val musicURI: String,

    @SerializedName("image_uri")
    val imageURI: String
)
