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
data class Houseware (
    val variant: String? = null,

    @SerializedName("body-title")
    val bodyTitle: String? = null,

    val pattern: String? = null,

    @SerializedName("pattern-title")
    val patternTitle: String? = null,

    val isDIY: Boolean,
    val canCustomizeBody: Boolean,
    val canCustomizePattern: Boolean,

    @SerializedName("kit-cost")
    val kitCost: String? = null,

    @SerializedName("color-1")
    val color1: String,

    @SerializedName("color-2")
    val color2: String,

    val size: String,
    val source: String,

    @SerializedName("source-detail")
    val sourceDetail: String,

    val version: String,

    @SerializedName("hha-concept-1")
    val hhaConcept1: String? = null,

    @SerializedName("hha-concept-2")
    val hhaConcept2: String? = null,

    @SerializedName("hha-series")
    val hhaSeries: String? = null,

    @SerializedName("hha-set")
    val hhaSet: String? = null,

    val isInteractive: Boolean,
    val tag: String,
    val isOutdoor: Boolean,

    @SerializedName("speaker-type")
    val speakerType: String? = null,

    @SerializedName("lighting-type")
    val lightingType: String? = null,

    val isCatalog: Boolean,

    @SerializedName("file-name")
    val fileName: String,

    @SerializedName("variant-id")
    val variantID: String? = null,

    @SerializedName("internal-id")
    val internalID: Long,

    val name: Name,

    @SerializedName("buy-price")
    val buyPrice: Long? = null,

    @SerializedName("sell-price")
    val sellPrice: Long,

    @SerializedName("image_uri")
    val imageURI: String
): Serializable