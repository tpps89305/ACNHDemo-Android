// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json = Json(JsonConfiguration.Stable)
// val fish = json.parse(Fish.serializer(), jsonString)

package com.dispy.acnhdemo.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

typealias Fishes = HashMap<String, Fish>

data class Fish (
    val id: Long,

    @SerializedName("file-name")
    val fileName: String,

    val name: Name,
    val availability: Availability,
    val shadow: String,
    val price: Long,

    @SerializedName("price-cj")
    val priceCj: Long,

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
) : Serializable {
    fun parsePriceInfo(): String {
        return price.toString()
    }
}

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
    val monthArrayNorthern: List<Long>,

    @SerializedName("month-array-southern")
    val monthArraySouthern: List<Long>,

    @SerializedName("time-array")
    val timeArray: List<Long>
)